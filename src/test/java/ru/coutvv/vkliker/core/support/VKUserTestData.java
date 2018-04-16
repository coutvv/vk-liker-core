package ru.coutvv.vkliker.core.support;

import com.google.gson.Gson;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import ru.coutvv.vkliker.core.api.support.ScriptExecutor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * user objects for VK API requests
 *
 * @author coutvv
 */
public class VKUserTestData {

    private final UserActor actor;
    private final VkApiClient vk;

    public VKUserTestData() throws Exception {
        this(
                new UserActor(Integer.parseInt(propsFromFile().getProperty("userId")), propsFromFile().getProperty("token")),
                new VkApiClient(HttpTransportClient.getInstance(), new Gson())
        );
    }

    public VKUserTestData(UserActor actor, VkApiClient vk) {
        this.actor = actor;
        this.vk = vk;
    }

    public ScriptExecutor vkScriptExecutor() {
        return new ScriptExecutor(vk, actor);
    }

    public Integer userId() {
        return actor.getId();
    }

    private static List<Properties> propsCache = new ArrayList<>();

    private static Properties propsFromFile() throws Exception {
        if(propsCache.size() > 0) return propsCache.get(0);
        try(InputStream in = VKUserTestData.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties props = new Properties();
            props.load(in);
            propsCache.add(props);
            return props;
        } catch (IOException e) {
            throw new Exception("can't load app.properties", e);
        }
    }
}