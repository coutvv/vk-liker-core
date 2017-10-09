package ru.coutvv.vkliker.core.vkapi.posts.entity;

import com.google.gson.annotations.SerializedName;
import ru.coutvv.vkliker.core.vkapi.posts.entity.attachment.*;

/**
 * @author coutvv
 */
public class Attachment {

    @SerializedName("type")
    private String type;

    @SerializedName("doc")
    private Document doc;

    @SerializedName("photo")
    private Photo photo;

    @SerializedName("video")
    private Video video;

    @SerializedName("audio")
    private Audio audio;

    @SerializedName("link")
    private Link link;


    public String getType() {
        return type;
    }

    public AbstractAttachment get() {
        AbstractAttachment result = null;
        switch(type) {
            case "doc":
                return doc;
            case "photo":
                return photo;
            case "audio":
                return audio;
            case "video":
                return video;
            case "link" :
                return link;
            default:
                return null;

        }
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "type='" + type + '\'' +
                ", doc=" + doc +
                ", photo=" + photo +
                ", video=" + video +
                ", audio=" + audio +
                ", link=" + link +
                '}';
    }
}
