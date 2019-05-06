package ru.coutvv.vkliker.core.api.storage;

import ru.coutvv.vkliker.core.api.entity.post.Post;
import ru.coutvv.vkliker.core.api.support.raw.Json;

import java.util.List;

/**
 * @author coutvv    15.04.2018
 */
public interface PostSource {

    List<Post> posts(int count, int offset) throws Exception;

    List<Json> rawPosts(int count, int offset) throws Exception;

    int quantityPosts() throws Exception;
}

