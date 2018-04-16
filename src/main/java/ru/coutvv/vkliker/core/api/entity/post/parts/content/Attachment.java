package ru.coutvv.vkliker.core.api.entity.post.parts.content;

import ru.coutvv.vkliker.core.api.support.raw.Json;

/**
 * @author coutvv    15.04.2018
 */
public class Attachment {
    private String type;
    private Media media;

    public Attachment(Json json) {
        this(
                json.stringField("type"),
                media(json)
        );
    }

    public Attachment(String type, Media media) {
        this.type = type;
        this.media = media;
    }

    private static Media media(Json json) {
        String type = json.stringField("type");
        switch (type) {

            case "photo" :
                return new Photo(json.objField(type));
            default:
                return new Media(); //abstract media
        }
    }

    public static class Media {}
}
