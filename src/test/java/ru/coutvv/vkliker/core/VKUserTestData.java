package ru.coutvv.vkliker.core;

import com.google.gson.Gson;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * user objects for VK API requests
 *
 * @author coutvv
 */
public class VKUserTestData {

    private static VKUserTestData instance;

    public String token;
    public Integer userId;

    public UserActor actor;
    public VkApiClient vk;

    private VKUserTestData() throws RuntimeException {

        Properties props = new Properties();

        InputStream in = VKUserTestData.class.getClassLoader().getResourceAsStream("app.properties");
        try(in) {
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("can't load app.properties", e);
        }

        token = props.getProperty("token");
        userId = Integer.parseInt(props.getProperty("userId"));

        actor = new UserActor(userId, token);
        TransportClient tc = HttpTransportClient.getInstance();
        vk = new VkApiClient(tc, new Gson());

    }

    public static VKUserTestData getInstance() throws RuntimeException {
        VKUserTestData result = instance;
        if(result == null) {
            synchronized (VKUserTestData.class) {
                result = instance;
                if(instance == null)
                    instance = result = new VKUserTestData();
            }
        }
        return result;
    }

}