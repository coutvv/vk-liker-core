package ru.coutvv.vkliker.core.api.entity.post.parts;

import ru.coutvv.vkliker.core.api.support.raw.Json;
import ru.coutvv.vkliker.core.api.entity.LikableObject;

/**
 * @author coutvv    15.04.2018
 */
public class Meta {

    private long id;
    private long date;
    private long ownerId;


    public Meta(Json json) {
        this(json.longField("id"),
             json.longField("date"),
             json.longField("owner_id"));
    }

    public Meta(Json json, String nameId, String nameOwner) {

        this(json.longField(nameId),
                json.longField("date"),
                json.longField(nameOwner));

    }

    public Meta(long id, long date, long ownerId) {
        this.id = id;
        this.date = date;
        this.ownerId = ownerId;
    }

    public LikableObject likableMetadata(String type, String accessKey) {
        return new LikableObject(type, ownerId, id, accessKey);
    }
}
