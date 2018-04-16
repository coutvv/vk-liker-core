package ru.coutvv.vkliker.core.api.storage.impl;

import ru.coutvv.vkliker.core.api.storage.Wall;
import ru.coutvv.vkliker.core.api.support.ScriptExecutor;
import ru.coutvv.vkliker.core.api.support.raw.Json;

import java.util.List;

/**
 * @author coutvv    16.04.2018
 */
public class RemoteWall implements Wall {

    private final ScriptExecutor executor;

    public RemoteWall(ScriptExecutor executor) {
        this.executor = executor;
    }

    private static final String SCRIPT_TEMPLATE = "return API.wall.get({\"owner_id\"  : %1$s, \"count\" : %2$d, \"filter\" : \"owner\", \"offset\" : %3$d});";

    @Override
    public List<Json> lastHundredPosts(Integer id) throws Exception {

        String script = String.format(SCRIPT_TEMPLATE, id, 100, 0);

        return executor.raw(script).arrField("items");
    }
}
