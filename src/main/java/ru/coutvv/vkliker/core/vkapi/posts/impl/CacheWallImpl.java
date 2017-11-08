package ru.coutvv.vkliker.core.vkapi.posts.impl;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import ru.coutvv.vkliker.core.fs.JsonFile;
import ru.coutvv.vkliker.core.vkapi.json.VkRequest;
import ru.coutvv.vkliker.core.vkapi.posts.AbstractWall;
import ru.coutvv.vkliker.core.vkapi.posts.entity.Post;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Cache wall implementation.
 *
 * @author coutvv
 */
public class CacheWallImpl extends AbstractWall {

    /**
     * Template for cache filename
     */
    private static String cacheStoragePath = "cache/wall/%s.json";

    /**
     * File for json caching
     */
    private final JsonFile cache;

    public CacheWallImpl(String userId, VkRequest vk) throws ClientException, ApiException, IOException {
        super(userId, vk);

        String cacheFilename = String.format(cacheStoragePath, userId);

        cache = new JsonFile(cacheFilename);

        if(cache.isEmpty()) {
            getWholeWall();
            writeCacheFile();
        } else {
            JsonElement content = cache.read();
            Type type = new TypeToken<List<Post>>(){}.getType();
            List<Post> posts = new Gson().fromJson(content, type);
            posts.forEach(p ->  wall.put(p.getId(), p));
            List<Post> newPosts = refresh();
            if(newPosts.size() > 0)
                writeCacheFile(); //update cache!
        }
    }

    @Override
    public synchronized List<Post> refresh() throws ClientException, ApiException {
        List<Post> result = super.refresh();
        if(result.size() > 0) {
            try {
                writeCacheFile();
            } catch(IOException e) {
                //TODO: log here pls
            }
        }
        return result;
    }

    public static void setCacheStoragePath(final String path) {
        cacheStoragePath = path;
    }

    private void writeCacheFile() throws IOException {
        List<Post> posts = new ArrayList<>(wall.values());
        posts.sort((p1, p2) -> (int)(p2.getId() - p1.getId()));
        JsonElement cacheObject = new Gson().toJsonTree(posts);
        cache.write(cacheObject);
    }
}
