package ru.coutvv.vkliker.core.vkapi.json;

import com.google.gson.JsonElement;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import ru.coutvv.vkliker.core.vkapi.util.LagUtil;

/**
 * Main class to request vk Api for getting JSON
 *
 * @author coutvv
 */
public class VkRequest {


    private final UserActor actor;
    private final VkApiClient vk;

    public VkRequest(UserActor actor, VkApiClient vk) {
        this.actor = actor;
        this.vk = vk;
    }

    /**
     * request to VK API with standard delay
     *
     * @param script
     * @return
     * @throws ClientException
     * @throws ApiException
     */
    public JsonElement execute(String script) throws ClientException, ApiException {
        return execute(script, 1);
    }

    /**
     * request to VK API with defined delay
     *
     * @param script
     * @param delay
     * @return
     * @throws ClientException
     * @throws ApiException
     */
    public JsonElement execute(String script, int delay) throws ClientException, ApiException {
        JsonElement result = vk.execute().code(actor, script).execute();
        LagUtil.lagX(delay);
        return result;
    }
}
