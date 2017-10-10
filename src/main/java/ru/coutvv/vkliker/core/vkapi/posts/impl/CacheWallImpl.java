package ru.coutvv.vkliker.core.vkapi.posts.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import ru.coutvv.vkliker.core.fs.JsonFile;
import ru.coutvv.vkliker.core.vkapi.json.VkRequest;
import ru.coutvv.vkliker.core.vkapi.posts.Wall;
import ru.coutvv.vkliker.core.vkapi.posts.entity.Post;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Cache wall implementation.
 *
 * @author coutvv
 */
public class CacheWallImpl implements Wall {

    private final VkRequest vk;
    private final String userId;

    /**
     * All posts on the wall
     */
    private final Map<Long, Post> wall = new HashMap<>();

    /**
     * Template for cache filname
     */
    private final String CACHE_TEMPLATE = "cache/wall/%s.json";

    /**
     * File for json caching
     */
    private final JsonFile cache;



    public CacheWallImpl(String userId, VkRequest vk) throws ClientException, ApiException, IOException {
        this.userId = userId;
        this.vk = vk;

        String cacheFilename = String.format(CACHE_TEMPLATE, userId);

        cache = new JsonFile(cacheFilename);

        if(!cache.isEmpty()) {
            JsonElement content = cache.read();
            Type type = new TypeToken<List<Post>>(){}.getType();
            List<Post> posts = new Gson().fromJson(content, type);
            posts.forEach(p ->  wall.put(p.getId(), p));
            refresh();
        } else {
            getWholeWall().forEach(post -> wall.put(post.getId(), post));
        }
    }



    @Override
    public synchronized List<Post> refresh() throws ClientException, ApiException {

        String script = String.format(GET_SCRIPT_TEMPLATE, userId, 1, 0);

        JsonElement json = vk.execute(script);

        JsonArray newPostsJson = json.getAsJsonObject().get(ITEMS_FIELD).getAsJsonArray();

        Post[] newPosts = new Gson().fromJson(newPostsJson, Post[].class);
        if(newPosts.length > 0 && wall.get(newPosts[0].getId()) == null) {
            long lastId = Collections.max(wall.keySet());
            int count = (int) (newPosts[0].getId() - lastId);


            for(int offset = 1; offset <= count; offset+=100) {
                int countOf = count - offset > 100 ? 100 : count - offset;
                script = String.format(GET_SCRIPT_TEMPLATE, userId, countOf, 0);
                JsonElement temp = vk.execute(script, 6);
                JsonArray items = temp.getAsJsonObject().get(ITEMS_FIELD).getAsJsonArray();
                newPostsJson.addAll(items);

            }
            newPosts = new Gson().fromJson(newPostsJson, Post[].class);

        }

        Arrays.stream(newPosts).forEach(post -> wall.put(post.getId(), post));

        return Arrays.asList(newPosts);
    }

    @Override
    public List<Post> getAll() {
        return new ArrayList<>(wall.values());
    }

    @Override
    public List<Post> getPostsSince(LocalDate date) {
        return getPostsBetween(date, LocalDate.now());
    }

    @Override
    public List<Post> getPostsBetween(LocalDate begin, LocalDate end) {

        long unixBegin = begin.atStartOfDay(ZoneId.systemDefault()).toEpochSecond(),
             unixEnd = end.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();

        return wall.values().stream()
                .filter(p -> p.getDate() > unixBegin && p.getDate() < unixEnd)
                .collect(Collectors.toList());

    }

    @Override
    public List<Post> getPosts(int count) {

        List<Post> posts = new ArrayList<>(wall.values());
        posts.sort((p1, p2) -> (int) (p1.getDate() - p2.getDate()));

        return posts.stream().limit(count).collect(Collectors.toList());
    }

    private final static String COUNT_FIELD = "count"; // field in request json with count of posts
    private final static String ITEMS_FIELD = "items"; // field in request json contains posts


    private List<Post> getWholeWall() throws ClientException, ApiException, IOException {
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

        cache.write(jsonPosts);

        Post[] result = new Gson().fromJson(jsonPosts, Post[].class);
        return Arrays.asList(result);
    }



}
