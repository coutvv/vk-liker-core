package ru.coutvv.vkliker.core.api.storage;

import org.junit.Test;
import org.testng.Assert;
import ru.coutvv.vkliker.core.api.storage.impl.VkFeed;
import ru.coutvv.vkliker.core.api.support.ScriptExecutor;
import ru.coutvv.vkliker.core.support.VKUserTestData;

/**
 * @author coutvv    17.04.2018
 */
public class TestVkFeed {

    ScriptExecutor executor = new VKUserTestData().vkScriptExecutor();

    public TestVkFeed() throws Exception {}

    @Test
    public void newsGetting() throws Exception {
        final VkFeed feed = new VkFeed(executor);
        final int COUNT_OF_POSTS = 100;

        Assert.assertEquals(
                feed.posts(COUNT_OF_POSTS, 0).size(),
                COUNT_OF_POSTS
        );
    }
}
