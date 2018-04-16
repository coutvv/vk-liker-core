package ru.coutvv.vkliker.core.api.entity;

import org.testng.annotations.Test;
import ru.coutvv.vkliker.core.api.entity.post.Post;
import ru.coutvv.vkliker.core.api.entity.post.impl.WallPost;
import ru.coutvv.vkliker.core.api.support.raw.GsonJson;
import ru.coutvv.vkliker.core.fs.JsonFile;
import ru.coutvv.vkliker.core.fs.SimpleJsonFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author coutvv    14.04.2018
 */
public class TestElegantPost {

    @Test
    public void fromCacheElegant() throws IOException {

        JsonFile source = new SimpleJsonFile("cache/wall/1.json");
        List<Post> posts = new ArrayList<>();
        source.content().getAsJsonArray().iterator().forEachRemaining(postJson -> {
            posts.add(new WallPost(new GsonJson(postJson.toString())));
        });
        System.out.println("done");
    }
}
