package ru.coutvv.vkliker.core.vkapi.posts.entity.attachment;

import com.google.gson.annotations.SerializedName;

/**
 * @author coutvv
 */
public class Photo implements AbstractAttachment {

    @SerializedName("id")
    private Long id;
    @SerializedName("album_id")
    private Long albumId;
    @SerializedName("owner_id")
    private Long ownerId;
    @SerializedName("width")
    private Long width;
    @SerializedName("height")
    private Long height;
    @SerializedName("date")
    private Long date;
    @SerializedName("post_id")
    private Long postId;

    @SerializedName("text")
    private String text;
    @SerializedName("access_key")
    private String accessKey;
    @SerializedName("photo_75")
    private String photo75;
    @SerializedName("photo_130")
    private String photo130;
    @SerializedName("photo_604")
    private String photo604;
    @SerializedName("photo_807")
    private String photo807;
    @SerializedName("photo_1280")
    private String photo1280;
    @SerializedName("photo_2560")
    private String photo2560;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getPhoto75() {
        return photo75;
    }

    public void setPhoto75(String photo75) {
        this.photo75 = photo75;
    }

    public String getPhoto130() {
        return photo130;
    }

    public void setPhoto130(String photo130) {
        this.photo130 = photo130;
    }

    public String getPhoto604() {
        return photo604;
    }

    public void setPhoto604(String photo604) {
        this.photo604 = photo604;
    }

    public String getPhoto807() {
        return photo807;
    }

    public void setPhoto807(String photo807) {
        this.photo807 = photo807;
    }

    public String getPhoto1280() {
        return photo1280;
    }

    public void setPhoto1280(String photo1280) {
        this.photo1280 = photo1280;
    }

    public String getPhoto2560() {
        return photo2560;
    }

    public void setPhoto2560(String photo2560) {
        this.photo2560 = photo2560;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", albumId=" + albumId +
                ", ownerId=" + ownerId + " link : http://vk.com/id" + ownerId + " " +
                ", width=" + width +
                ", height=" + height +
                ", date=" + date +
                ", postId=" + postId +
                ", text='" + text + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", photo75='" + photo75 + '\'' +
                ", photo130='" + photo130 + '\'' +
                ", photo604='" + photo604 + '\'' +
                ", photo807='" + photo807 + '\'' +
                ", photo1280='" + photo1280 + '\'' +
                ", photo2560='" + photo2560 + '\'' +
                '}';
    }
}
