package ru.coutvv.vkliker.core.api.storage;

import ru.coutvv.vkliker.core.api.entity.post.Post;

import java.util.List;

/**
 * @author coutvv    15.04.2018
 */
public interface PostSource {

    /**
     *
     * @param count
     * @param offset
     * @return
     * @throws Exception
     */
    List<Post> posts(int count, int offset) throws Exception;

}

