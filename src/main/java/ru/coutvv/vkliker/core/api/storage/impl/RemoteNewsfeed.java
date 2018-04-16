package ru.coutvv.vkliker.core.api.storage.impl;

import ru.coutvv.vkliker.core.api.storage.Newsfeed;
import ru.coutvv.vkliker.core.api.support.ScriptExecutor;
import ru.coutvv.vkliker.core.api.support.raw.Json;

import java.util.List;

/**
 * @author coutvv    16.04.2018
 */
public class RemoteNewsfeed implements Newsfeed {

    private final ScriptExecutor executor;

    public RemoteNewsfeed(ScriptExecutor executor) {
        this.executor = executor;
    }

    private static final String script = "return API.newsfeed.get({\"filters\"  : \"post\", \"count\" : 100, });";


    @Override
    public List<Json> lastRawPosts() throws Exception {
        return executor.raw(script).arrField("items");
    }
}
