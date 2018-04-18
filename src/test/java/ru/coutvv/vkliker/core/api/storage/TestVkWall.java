package ru.coutvv.vkliker.core.api.storage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.coutvv.vkliker.core.api.entity.post.Post;
import ru.coutvv.vkliker.core.api.storage.impl.VkWall;
import ru.coutvv.vkliker.core.api.support.Delay;
import ru.coutvv.vkliker.core.support.VKUserTestData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coutvv    17.04.2018
 */
public class TestVkWall {

    private VKUserTestData testData;
    private final Delay delay = new Delay(1000);

    @BeforeClass
    public void setup() throws Exception {
        testData = new VKUserTestData();
    }

    @Test
    public void gettingWholeWall() throws Exception {
        PostSource userWall = new VkWall(testData.vkScriptExecutor(), testData.userId());
        int count = 100, offset = 0;

        final List<Post> wholeWall = new ArrayList<>();

        List<Post> posts;
        do { //TODO: move this code to object & rewrite this test
            posts = userWall.posts(count, offset);
            wholeWall.addAll(posts);
            offset += count;
            delay.apply();
        } while(posts.size() != 0);
        System.out.println("nice!");
    }

}
