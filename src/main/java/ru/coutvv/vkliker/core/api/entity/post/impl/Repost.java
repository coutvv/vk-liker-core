package ru.coutvv.vkliker.core.api.entity.post.impl;

import ru.coutvv.vkliker.core.api.entity.post.Post;
import ru.coutvv.vkliker.core.api.entity.post.parts.LikeInfo;
import ru.coutvv.vkliker.core.api.support.raw.Json;

/**
 * @author coutvv    16.04.2018
 */
public class Repost extends Post {
    public Repost(Json json) {
        super(json, new LikeInfo.NotLoadedLikeInfo());
    }
}
