package ru.coutvv.vkliker.core.vkapi.posts.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Vk Post
 */
public class Post implements Serializable{

    @SerializedName("id")
    private Long id;
    @SerializedName("type")
    private String type;
    @SerializedName("source_id")
    private Long sourceId;
    @SerializedName("date")
    private Long date;
    @SerializedName("post_id")
    private Long postId;
    @SerializedName("post_type")
    private String postType;
    @SerializedName("text")
    private String text;
    @SerializedName("signer_id")
    private Long signerId;
    @SerializedName("owner_id")
    private Long ownerId;
    @SerializedName("attachments")
    private Attachment[] attachments;
    @SerializedName("copy_history")
    private Post[] repost;

    private LikesInfo likes;

    public Post[] getRepost() {
        return repost;
    }

    public void setRepost(Post[] repost) {
        this.repost = repost;
    }


    public Attachment[] getAttachments() {
        return attachments;
    }

    public void setAttachments(Attachment[] attachments) {
        this.attachments = attachments;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getDate() {
        return date;
    }

    public LikesInfo getLikes() {
        return likes;
    }

    public void setLikes(LikesInfo likes) {
        this.likes = likes;
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

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getSignerId() {
        return signerId;
    }

    public void setSignerId(Long signerId) {
        this.signerId = signerId;
    }

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

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("============\t============\n|")
                .append(sdf.format(new Date(date*1000)))
                .append("|\t type: ")
                .append(postType )
                .append("\n============\t============\n")
                .append(text)
                .append("\n");
        if(repost != null && repost.length > 0) {
            result.append(Arrays.toString(repost)).append(" \n");
        }
        if(attachments != null && attachments.length > 0) {
            final String template = "attachement count: %s \n";
            result.append(String.format(template, attachments.length));
        }

        return result.toString();
    }

}
