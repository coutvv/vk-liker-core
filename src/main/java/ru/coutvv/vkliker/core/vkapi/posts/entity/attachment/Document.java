package ru.coutvv.vkliker.core.vkapi.posts.entity.attachment;

import com.google.gson.annotations.SerializedName;

/**
 * @author coutvv
 */
public class Document implements AbstractAttachment {


    @SerializedName("id")
    private Long id;
    @SerializedName("owner_id")
    private Long ownerId;
    @SerializedName("size")
    private Long size;
    @SerializedName("date")
    private Long date;
    @SerializedName("type")
    private Long type;

    @SerializedName("title")
    private String title;
    @SerializedName("ext")
    private String ext;
    @SerializedName("url")
    private String url;
    @SerializedName("access_key")
    private String access_key;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccess_key() {
        return access_key;
    }

    public void setAccess_key(String access_key) {
        this.access_key = access_key;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", size=" + size +
                ", date=" + date +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", ext='" + ext + '\'' +
                ", url='" + url + '\'' +
                ", access_key='" + access_key + '\'' +
                '}';
    }
}
