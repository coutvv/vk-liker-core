package ru.coutvv.vkliker.core.api.entity.post.parts.content;

import ru.coutvv.vkliker.core.api.support.raw.Json;
import ru.coutvv.vkliker.core.api.entity.post.parts.Meta;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coutvv    15.04.2018
 */
public class Photo extends Attachment.Media {
    private Meta meta;
    private String text;
    private List<String> urls;

    public Photo(Json json) {
        this(
                new Meta(json),
                json.stringField("text"),
                photoUrls(json)
        );
    }

    public Photo(Meta meta, String text, List<String> urls) {
        this.meta = meta;
        this.text = text;
        this.urls = urls;
    }

    private static final String[] URL_NAMES = {"photo_75", "photo_130", "photo_604",
            "photo_807", "photo_1280", "photo_2560",};

    private static List<String> photoUrls(Json json) {
        List<String> result = new ArrayList<>();
        for(String urlName : URL_NAMES) {
            String url = json.stringField(urlName);
            if(!url.equals("")) result.add(url);
        }
        return result;
    }
}
