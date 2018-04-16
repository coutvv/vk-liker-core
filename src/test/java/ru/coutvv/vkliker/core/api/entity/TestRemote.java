package ru.coutvv.vkliker.core.api.entity;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import org.testng.annotations.Test;
import ru.coutvv.vkliker.core.VKUserTestData;
import ru.coutvv.vkliker.core.api.entity.post.Post;
import ru.coutvv.vkliker.core.api.entity.post.impl.NewsPost;
import ru.coutvv.vkliker.core.api.storage.Newsfeed;
import ru.coutvv.vkliker.core.api.storage.Wall;
import ru.coutvv.vkliker.core.api.storage.impl.RemoteNewsfeed;
import ru.coutvv.vkliker.core.api.storage.impl.RemoteWall;
import ru.coutvv.vkliker.core.api.support.ScriptExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coutvv    15.04.2018
 */
public class TestRemote {
    VkApiClient vk = VKUserTestData.getInstance().vk;
    UserActor actor = VKUserTestData.getInstance().actor;
    ScriptExecutor executor = new ScriptExecutor(vk, actor);

    @Test
    public void testWall() throws Exception {
        Wall wall = new RemoteWall(executor);
        System.out.println("done");
        List<Post> posts = new ArrayList<>();
        wall.lastHundredPosts(actor.getId()).forEach(json -> {
            Post post = new NewsPost(json);
            posts.add(post);
        });
//        Like like = new RemoteLike(executor);
//        for(Post post: posts) {
//            like.add(post.likable().objectIdentity());
//        }
    }

    @Test
    public void testNewsFeedAndLike() throws Exception {
        Newsfeed simple = new RemoteNewsfeed(executor);
        simple.lastRawPosts().forEach(json -> new NewsPost(json));
    }
}
