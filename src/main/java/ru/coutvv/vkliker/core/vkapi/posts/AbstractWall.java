package ru.coutvv.vkliker.core.vkapi.posts;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import ru.coutvv.vkliker.core.vkapi.json.VkRequest;
import ru.coutvv.vkliker.core.vkapi.posts.entity.Post;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * in memory wall without filling data
 */
public abstract class AbstractWall implements Wall {

    protected final VkRequest vk;
    private final String userId;

    /**
     * All posts on the wall
     */
    protected final Map<Long, Post> wall = new HashMap<>();

    public AbstractWall(final String userId, final VkRequest vk) {
        this.vk = vk;
        this.userId = userId;
    }

    @Override
    public synchronized List<Post> refresh() throws ClientException, ApiException {

        String script = String.format(GET_SCRIPT_TEMPLATE, userId, 1, 0);

        JsonElement json = vk.execute(script);

        JsonArray newPostsJson = json.getAsJsonObject().get(ITEMS_FIELD).getAsJsonArray();
        int count = json.getAsJsonObject().get("count").getAsInt();

        Post[] newPosts = new Gson().fromJson(newPostsJson, Post[].class);
        List<Post> result = new ArrayList<>();
        if(newPosts.length > 0 && wall.get(newPosts[0].getId()) == null) {
            result.add(newPosts[0]);
            long lastId = Collections.max(wall.keySet());
            boolean isNew = true;
            for(int offset = 1; offset <= count && isNew; offset+=100) {
                int countOf = count - offset > 100 ? 100 : count - offset;
                script = String.format(GET_SCRIPT_TEMPLATE, userId, countOf, 0);
                JsonElement temp = vk.execute(script, 6);
                JsonArray items = temp.getAsJsonObject().get(ITEMS_FIELD).getAsJsonArray();
                Post[] tempPosts = new Gson().fromJson(items, Post[].class);
                for(Post p : tempPosts) {
                    if(wall.get(p.getId()) != null) {
                        isNew = false;
                        break;
                    }
                    result.add(p);
                }
            }
        }

        result.forEach(post -> wall.put(post.getId(), post));

        return result;
    }

    @Override
    public List<Post> getAll() {
        return new ArrayList<>(wall.values());
    }

    @Override
    public List<Post> getPostsSince(final LocalDate date) {
        return getPostsBetween(date, LocalDate.now());
    }

    @Override
    public List<Post> getPostsBetween(final LocalDate begin, final LocalDate end) {

        long unixBegin = begin.atStartOfDay(ZoneId.systemDefault()).toEpochSecond(),
                unixEnd = end.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();

        return wall.values().stream()
                .filter(p -> p.getDate() > unixBegin && p.getDate() < unixEnd)
                .collect(Collectors.toList());

    }

    @Override
    public List<Post> getPosts(final int count) {

        List<Post> posts = new ArrayList<>(wall.values());
        posts.sort((p1, p2) -> (int) (p1.getDate() - p2.getDate()));

        return posts.stream().limit(count).collect(Collectors.toList());
    }



    private final static String COUNT_FIELD = "count"; // field in request json with count of posts
    private final static String ITEMS_FIELD = "items"; // field in request json contains posts

    /**
     * getting user's posts on wall script from vk
     */
    protected void fillWholeWall() throws ClientException, ApiException {
        String script = String.format(GET_SCRIPT_TEMPLATE, userId, 100, 0);

        JsonElement json = vk.execute(script);
        int count = json.getAsJsonObject().get(COUNT_FIELD).getAsInt();
        JsonArray resultArray = json.getAsJsonObject().get(ITEMS_FIELD).getAsJsonArray();
        for(int offset = 100; offset <= count; offset+=100) {
            script = String.format(GET_SCRIPT_TEMPLATE, userId, 100, offset);
            JsonElement temp = vk.execute(script, 6);
            JsonArray items = temp.getAsJsonObject().get(ITEMS_FIELD).getAsJsonArray();
            resultArray.addAll(items);
        }

        JsonArray jsonPosts = json.getAsJsonObject().get(ITEMS_FIELD).getAsJsonArray();

        Post[] result = new Gson().fromJson(jsonPosts, Post[].class);
        Arrays.asList(result).forEach(post -> wall.put(post.getId(), post));
    }
}
