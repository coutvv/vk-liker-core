package ru.coutvv.vkliker.core.app;

import com.google.gson.Gson;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.cactoos.scalar.NumberOf;
import ru.coutvv.vkliker.core.App;
import ru.coutvv.vkliker.core.Switch;
import ru.coutvv.vkliker.core.api.action.impl.RemoteLike;
import ru.coutvv.vkliker.core.api.entity.post.Post;
import ru.coutvv.vkliker.core.api.storage.PostSource;
import ru.coutvv.vkliker.core.api.storage.impl.VkFeed;
import ru.coutvv.vkliker.core.api.support.Delay;
import ru.coutvv.vkliker.core.api.support.ScriptExecutor;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Program that's loving to like all post in user feed
 *
 * @author coutvv    18.04.2018
 */
public class LimitlessLike implements App {

    private final ScriptExecutor executor;
    private final AtomicBoolean running;

    public LimitlessLike(Properties properties) throws Exception {
            this(new UserActor(
                    new NumberOf(properties.getProperty("userId")).intValue(),
                    properties.getProperty("token")
                ),
                new VkApiClient(HttpTransportClient.getInstance(), new Gson())
            );
    }

    public LimitlessLike(int userId, String token) {
        this(
                new UserActor(userId, token),
                new VkApiClient(HttpTransportClient.getInstance(), new Gson())
        );

    }

    public LimitlessLike(UserActor actor, VkApiClient vk) {
        this(
                new ScriptExecutor(vk, actor)
        );

    }

    public LimitlessLike(ScriptExecutor executor) {

        this.executor = executor;
        this.running = new AtomicBoolean(true);
    }

    @Override
    public void run() throws Exception {
        Delay fiveMin = new Delay(300_000);
        Delay betweenLike = new Delay(7_000);
        PostSource feed = new VkFeed(executor);
        while(running.get()) {
            for(Post post: feed.posts(20, 0)) {
                if(!post.liked() && running.get()) {
                    new RemoteLike(executor).add(post.likable().objectIdentity());
                    System.out.println("Post: " + post.toString() + " has liked!");
                    betweenLike.apply();
                }
            }
            fiveMin.apply();
        }
        System.out.println("App was stopped");
    }

    @Override
    public Switch control() {
        return () -> running.set(false);
    }

}
