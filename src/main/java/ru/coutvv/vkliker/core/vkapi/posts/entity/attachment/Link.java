package ru.coutvv.vkliker.core.vkapi.posts.entity.attachment;

import com.google.gson.annotations.SerializedName;

/**
 * @author coutvv
 */
public class Link  implements AbstractAttachment {

    @SerializedName("url")
    private String url;
    @SerializedName("description")
    private String description;
    @SerializedName("caption")
    private String caption;
    @SerializedName("title")
    private String title;
    @SerializedName("photo")
    private Photo photo;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Link{" +
                "url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", caption='" + caption + '\'' +
                ", title='" + title + '\'' +
                ", photo=" + photo +
                '}';
    }
}
