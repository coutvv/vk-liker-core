package ru.coutvv.vkliker.core.entity;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.coutvv.vkliker.core.VKUserTestData;
import ru.coutvv.vkliker.core.vkapi.json.VkRequest;
import ru.coutvv.vkliker.core.vkapi.posts.Wall;
import ru.coutvv.vkliker.core.vkapi.posts.entity.Post;
import ru.coutvv.vkliker.core.vkapi.posts.impl.CacheWallImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author coutvv
 */
public class TestPostEntity {


    private VKUserTestData testData = VKUserTestData.getInstance();
    private final String userId = "15733285";

    private static Wall wall;

    @BeforeClass
    public void setup() throws IOException, ApiException, ClientException {
        VkRequest vk = new VkRequest(testData.actor, testData.vk);

        wall  = new CacheWallImpl(userId, vk);
    }

    @Test
    public void testFields() {
        Map<String, Post> map = new HashMap<>();
        for(Post post : wall.getAll()) {
            map.put(post.getPostType(), post);
        }
        map.keySet().forEach(System.out::println);
    }
}
