package ru.coutvv.vkliker.core.vkapi.posts.entity.attachment;

import com.google.gson.annotations.SerializedName;

/**
 * @author coutvv
 */
public class Audio implements AbstractAttachment{

    @SerializedName("id")
    private Long id;
    @SerializedName("owner_id")
    private Long ownerId;
    @SerializedName("album_id")
    private Long albumId;
    @SerializedName("date")
    private Long date;
    @SerializedName("duration")
    private Long duration;
    @SerializedName("lyrics_id")
    private Long lyricsId;
    @SerializedName("genre_id")
    private Long genreId;
    @SerializedName("content_restricted")
    private Long contentRestricted;

    @SerializedName("artist")
    private String artist;
    @SerializedName("title")
    private String title;

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

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getLyricsId() {
        return lyricsId;
    }

    public void setLyricsId(Long lyricsId) {
        this.lyricsId = lyricsId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public Long getContentRestricted() {
        return contentRestricted;
    }

    public void setContentRestricted(Long contentRestricted) {
        this.contentRestricted = contentRestricted;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "id=" + id +
                ", ownerId=" + ownerId + " link : http://vk.com/id" + ownerId + " " +
                ", albumId=" + albumId +
                ", date=" + date +
                ", duration=" + duration +
                ", lyricsId=" + lyricsId +
                ", genreId=" + genreId +
                ", contentRestricted=" + contentRestricted +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
