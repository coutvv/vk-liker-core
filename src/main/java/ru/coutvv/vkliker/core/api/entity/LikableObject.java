package ru.coutvv.vkliker.core.api.entity;

import ru.coutvv.vkliker.core.api.support.raw.GsonJson;
import ru.coutvv.vkliker.core.api.support.raw.Json;

public class LikableObject {
    private String type;
    private long ownerId;
    private long itemId;
    private String accessKey;

    public LikableObject(String type, long ownerId, long itemId, String accessKey) {
        this.type = type;
        this.ownerId = ownerId;
        this.itemId = itemId;
        this.accessKey = accessKey;
    }

    private final static String IDENTITY_TEMPLATE = "{\"type\"  : \"%1$s\", \"owner_id\" : %2$d, \"item_id\" : \"%3$d\", \"access_key\" : \"%4$s\"}",
                                IDENTITY_TEMPLATE_BY = "{\"type\"  : \"%1$s\", \"owner_id\" : %2$d, \"item_id\" : \"%3$d\", \"access_key\" : \"%4$s\", \"user_id\": \"%5$d\"}";

    public Json objectIdentity() {
        return new GsonJson(String.format(IDENTITY_TEMPLATE, type, ownerId, itemId, accessKey));
    }
    public Json objectIdentityToUser(Long userId) {
        return new GsonJson(String.format(IDENTITY_TEMPLATE_BY, type, ownerId, itemId, accessKey, userId));
    }
}
