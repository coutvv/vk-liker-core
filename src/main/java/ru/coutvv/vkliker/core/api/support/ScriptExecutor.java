package ru.coutvv.vkliker.core.api.support;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import ru.coutvv.vkliker.core.api.support.raw.GsonJson;
import ru.coutvv.vkliker.core.api.support.raw.Json;

/**
 * @author coutvv    16.04.2018
 */
public class ScriptExecutor {

    private final VkApiClient vk;
    private final UserActor actor;

    public ScriptExecutor(VkApiClient vk, UserActor actor) {
        this.vk = vk;
        this.actor = actor;
    }

    public Json raw(String script) throws Exception {
        try {
            return new GsonJson(
                    vk.execute().code(actor, script).execute().toString()
            );
        } catch(Exception ex) {
            throw new Exception("Can't execute script to remote vk API: " + script, ex);
        }
    }
}
