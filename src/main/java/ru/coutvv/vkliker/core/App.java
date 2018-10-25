package ru.coutvv.vkliker.core;

import com.google.gson.Gson;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.cactoos.scalar.NumberOf;
import ru.coutvv.vkliker.core.api.support.ScriptExecutor;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class App {

    protected final ScriptExecutor executor;
    protected final AtomicBoolean running;

    public App(Properties properties) throws Exception {
        this(new UserActor(
                        new NumberOf(properties.getProperty("userId")).intValue(),
                        properties.getProperty("token")
                ),
                new VkApiClient(HttpTransportClient.getInstance(), new Gson())
        );
    }

    public App(int userId, String token) {
        this(
                new UserActor(userId, token),
                new VkApiClient(HttpTransportClient.getInstance(), new Gson())
        );

    }

    public App(UserActor actor, VkApiClient vk) {
        this(
                new ScriptExecutor(vk, actor)
        );

    }

    public App(ScriptExecutor executor) {

        this.executor = executor;
        this.running = new AtomicBoolean(true);
    }

    public abstract void run() throws Exception;
    public abstract Switch control();
}
