package ru.coutvv.vkliker.core.api.entity.post.impl;

import ru.coutvv.vkliker.core.api.entity.post.Post;
import ru.coutvv.vkliker.core.api.entity.post.parts.Content;
import ru.coutvv.vkliker.core.api.entity.post.parts.LikeInfo;
import ru.coutvv.vkliker.core.api.entity.post.parts.Meta;
import ru.coutvv.vkliker.core.api.support.raw.Json;

/**
 * @author coutvv    15.04.2018
 */
public class NewsPost extends Post {

    public NewsPost(Json json) {
        super(new Meta(json, "post_id", "source_id"),
                new Content(json),
                new LikeInfo(json.objField("likes")));
    }

}
