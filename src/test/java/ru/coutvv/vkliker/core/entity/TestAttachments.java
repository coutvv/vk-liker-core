package ru.coutvv.vkliker.core.entity;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.testng.annotations.Test;
import ru.coutvv.vkliker.core.VKUserTestData;
import ru.coutvv.vkliker.core.vkapi.json.VkRequest;
import ru.coutvv.vkliker.core.vkapi.posts.Wall;
import ru.coutvv.vkliker.core.vkapi.posts.entity.Attachment;
import ru.coutvv.vkliker.core.vkapi.posts.entity.Post;
import ru.coutvv.vkliker.core.vkapi.posts.impl.CacheWallImpl;

import java.io.IOException;

/**
 * @author coutvv
 */
public class TestAttachments {

    VKUserTestData testData = VKUserTestData.getInstance();
    final String userId = "15733285";

    @Test
    public void testAttachment() throws ClassNotFoundException, IOException, ApiException, ClientException {

        Wall cacheWall = new CacheWallImpl(userId, new VkRequest(testData.actor, testData.vk));

        for(Post post: cacheWall.getAll()) {
            if(post.getAttachments() != null && post.getAttachments().length>0) {
                System.out.println("have attachment!!!");
                System.out.println(post.getAttachments()[0]);
                return;
            }
        }
    }

    @Test
    public void testPhotoAttachment() throws ClassNotFoundException, IOException, ApiException, ClientException {

        Wall cacheWall = new CacheWallImpl(userId, new VkRequest(testData.actor, testData.vk));

        for(Post post: cacheWall.getAll()) {
            if(post.getAttachments() != null && post.getAttachments().length>0) {
                for(Attachment att : post.getAttachments()) {
                    if(att.getType().equals("photo")) {
                        System.out.println(att.get());
                        return;
                    }
                }
            }
        }
    }
}
