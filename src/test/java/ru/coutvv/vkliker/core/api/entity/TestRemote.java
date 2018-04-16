package ru.coutvv.vkliker.core.api.entity;

import org.testng.annotations.Test;
import ru.coutvv.vkliker.core.api.entity.post.Post;
import ru.coutvv.vkliker.core.api.entity.post.impl.NewsPost;
import ru.coutvv.vkliker.core.api.entity.post.impl.WallPost;
import ru.coutvv.vkliker.core.api.storage.Newsfeed;
import ru.coutvv.vkliker.core.api.storage.Wall;
import ru.coutvv.vkliker.core.api.storage.impl.RemoteNewsfeed;
import ru.coutvv.vkliker.core.api.storage.impl.RemoteWall;
import ru.coutvv.vkliker.core.support.VKUserTestData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coutvv    15.04.2018
 */
public class TestRemote {

    private VKUserTestData test = new VKUserTestData();

    public TestRemote() throws Exception {
    }

    @Test
    public void testWall() throws Exception {
        Wall wall = new RemoteWall(test.vkScriptExecutor());
        System.out.println("done");
        List<Post> posts = new ArrayList<>();
        wall.lastHundredPosts(test.userId()).forEach(json -> {
            Post post = new WallPost(json);
            posts.add(post);
        });
    }

    @Test
    public void testNewsFeedAndLike() throws Exception {
        Newsfeed simple = new RemoteNewsfeed(test.vkScriptExecutor());
        simple.lastRawPosts().forEach(NewsPost::new);
    }
}
