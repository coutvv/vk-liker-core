package ru.coutvv.vkliker.core.vkapi.posts.entity.attachment;

import com.google.gson.annotations.SerializedName;

/**
 * @author coutvv
 */
public class Video implements AbstractAttachment {

    @SerializedName("id")
    private Long id;

    @SerializedName("owner_id")
    private Long ownerId;

    @SerializedName("duration")
    private Long duration;

    @SerializedName("date")
    private Long date;

    @SerializedName("comments")
    private Long comments;

    @SerializedName("views")
    private Long views;

    @SerializedName("can_add")
    private Long canAdd;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("photo_130")
    private String photo130;

    @SerializedName("photo_320")
    private String photo320;

    @SerializedName("photo_800")
    private String photo800;

    @SerializedName("access_key")
    private String accessKey;

    @SerializedName("platform")
    private String platform;

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

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getCanAdd() {
        return canAdd;
    }

    public void setCanAdd(Long canAdd) {
        this.canAdd = canAdd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto130() {
        return photo130;
    }

    public void setPhoto130(String photo130) {
        this.photo130 = photo130;
    }

    public String getPhoto320() {
        return photo320;
    }

    public void setPhoto320(String photo320) {
        this.photo320 = photo320;
    }

    public String getPhoto800() {
        return photo800;
    }

    public void setPhoto800(String photo800) {
        this.photo800 = photo800;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "Video {" +
                "id=" + id +
                ", ownerId=" + ownerId + " link : http://vk.com/id" + ownerId + " " +
                ", duration=" + duration +
                ", date=" + date +
                ", comments=" + comments +
                ", views=" + views +
                ", canAdd=" + canAdd +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", photo130='" + photo130 + '\'' +
                ", photo320='" + photo320 + '\'' +
                ", photo800='" + photo800 + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }
}
