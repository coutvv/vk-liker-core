package ru.coutvv.vkliker.core.app;

import ru.coutvv.vkliker.core.App;
import ru.coutvv.vkliker.core.Switcher;
import ru.coutvv.vkliker.core.api.action.impl.RemoteLike;
import ru.coutvv.vkliker.core.api.entity.post.Post;
import ru.coutvv.vkliker.core.api.storage.PostSource;
import ru.coutvv.vkliker.core.api.storage.post.VkFeed;
import ru.coutvv.vkliker.core.api.support.Delay;

import java.util.Properties;

/**
 * Program that's loving to like all post in user feed
 *
 * @author coutvv    18.04.2018
 */
public class LimitlessLike extends App {

    public LimitlessLike(Properties properties) {
        super(properties);
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
    public Switcher control() {
        return () -> running.set(false);
    }

}
