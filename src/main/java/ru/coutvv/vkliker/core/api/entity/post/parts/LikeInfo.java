package ru.coutvv.vkliker.core.api.entity.post.parts;

import ru.coutvv.vkliker.core.api.support.raw.Json;

/**
 * @author coutvv    15.04.2018
 */
public class LikeInfo {
    private long count;
    private long userLikes;
    private long canLike;
    private long canPublish;

    private LikeInfo() {}

    public LikeInfo(Json json) {
        this(   json.longField("count"),
                json.longField("user_likes"),
                json.longField("can_like"),
                json.longField("can_publish"));
    }

    public LikeInfo(long count, long userLikes, long canLike, long canPublish) {
        this.count = count;
        this.userLikes = userLikes;
        this.canLike = canLike;
        this.canPublish = canPublish;
    }

    public boolean liked() {
        return canLike == 0;
    }

    public static class NotLoadedLikeInfo extends LikeInfo {
    }
}
