package ru.coutvv.vkliker.core.api.storage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.coutvv.vkliker.core.api.entity.post.Post;
import ru.coutvv.vkliker.core.api.storage.impl.VkWall;
import ru.coutvv.vkliker.core.api.support.Delay;
import ru.coutvv.vkliker.core.api.support.ScriptExecutor;
import ru.coutvv.vkliker.core.support.PropertiesForTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coutvv    17.04.2018
 */
public class TestVkWall {

    private final Delay delay = new Delay(1000);

    int userId;
    ScriptExecutor scriptExecutor;

    @BeforeClass
    public void setup() throws Exception {
        PropertiesForTest props = new PropertiesForTest();

        userId = props.intValue("userId");
        scriptExecutor = new ScriptExecutor(userId, props.value("token"));
    }

    @Test
    public void gettingWholeWall() throws Exception {
        PostSource userWall = new VkWall(scriptExecutor, userId);
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
