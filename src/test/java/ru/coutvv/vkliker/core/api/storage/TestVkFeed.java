package ru.coutvv.vkliker.core.api.storage;

import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.Assert;
import ru.coutvv.vkliker.core.api.storage.impl.VkFeed;
import ru.coutvv.vkliker.core.api.support.ScriptExecutor;
import ru.coutvv.vkliker.core.support.PropertiesForTest;

import java.io.IOException;

/**
 * @author coutvv    17.04.2018
 */
public class TestVkFeed {

    PropertiesForTest props;

    @BeforeClass
    public void setup() throws IOException {
        props = new PropertiesForTest();
    }

    @Test
    public void newsGetting() throws Exception {
        VkFeed feed = new VkFeed(
                new ScriptExecutor(
                    props.intValue("userId"),
                    props.value("token")
                )
        );

        final int COUNT_OF_POSTS = 100;

        Assert.assertEquals(
                feed.posts(COUNT_OF_POSTS, 0).size(),
                COUNT_OF_POSTS
        );
    }
}
