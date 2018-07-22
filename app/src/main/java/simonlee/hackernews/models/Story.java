package simonlee.hackernews.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Story extends Item {

    @SerializedName("descentants")
    protected long descendants;

    @SerializedName("by")
    protected String author;

    @SerializedName("kids")
    protected List<Long> commentIds;

    @SerializedName("time")
    protected long createdAt;

    @SerializedName("title")
    protected String title;

    @SerializedName("type")
    protected String type;

    @SerializedName("url")
    protected String link;

    public long getDescendants() {
        return descendants;
    }

    public void setDescendants(long descendants) {
        this.descendants = descendants;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Long> getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(List<Long> commentIds) {
        this.commentIds = commentIds;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
