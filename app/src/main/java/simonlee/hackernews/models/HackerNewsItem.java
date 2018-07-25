package simonlee.hackernews.models;


import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class HackerNewsItem {

    public static class Item {

        // the item type
        @Retention(RetentionPolicy.SOURCE)
        @StringDef({
                ITEM_TYPE_STORY,
                ITEM_TYPE_JOB,
                ITEM_TYPE_POLL,
                ITEM_TYPE_POLLOPT,
                ITEM_TYPE_COMMENT
        })
        public @interface ItemType {
        }

        public static final String ITEM_TYPE_STORY = "story";
        public static final String ITEM_TYPE_JOB = "job";
        public static final String ITEM_TYPE_COMMENT = "comment";
        public static final String ITEM_TYPE_POLL = "poll";
        public static final String ITEM_TYPE_POLLOPT = "pollopt";

        // The item's unique id.
        @SerializedName("id")
        private Integer id;
        // The type of item. One of "job", "story", "comment", "poll", or "pollopt".
        @SerializedName("type")
        private String type;
        // `true` if the item is deleted.
        @SerializedName("deleted")
        private Boolean deleted;
        // The username of the item's author.
        @SerializedName("by")
        private String author;
        // Creation date of the item, in Unix Time.
        @SerializedName("time")
        private Long creationTime;
        // The comment, story or poll text. HTML.
        @SerializedName("text")
        private String content;
        // `true` if the item is dead.
        @SerializedName("dead")
        private Boolean dead;
        // The comment's parent: either another comment or the relevant story.
        @SerializedName("parent")
        private Long parentId;
        // The pollopt's associated poll.
        @SerializedName("poll")
        private Long pollId;
        // The ids of the item's comments, in ranked display order.
        @SerializedName("kids")
        private List<Long> commentIds;
        // The URL of the story.
        @SerializedName("url")
        private String rawUrl;
        // The story's score, or the votes for a pollopt.
        @SerializedName("score")
        private Integer score;
        // The title of the story, poll or job.
        @SerializedName("title")
        private String title;
        // A list of related pollopts, in display order.
        @SerializedName("parts")
        private List<Long> partIds;
        // In the case of stories or polls, the total comment count.
        @SerializedName("descendants")
        private Integer descendants;

        public Item(Integer id) {
            this.id = id;
        }

        public
        @NonNull
        Integer getId() {
            return id;
        }

        public void setId(@NonNull Integer id) {
            this.id = id;
        }

        public
        @ItemType
        String getType() {
            return type;
        }

        public void setType(@ItemType String type) {
            this.type = type;
        }

        public Boolean getDeleted() {
            return deleted;
        }

        public void setDeleted(Boolean deleted) {
            this.deleted = deleted;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public Long getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(Long creationTime) {
            this.creationTime = creationTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Boolean getDead() {
            return dead;
        }

        public void setDead(Boolean dead) {
            this.dead = dead;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        public Long getPollId() {
            return pollId;
        }

        public void setPollId(Long pollId) {
            this.pollId = pollId;
        }

        public List<Long> getCommentIds() {
            return commentIds;
        }

        public void setCommentIds(List<Long> commentIds) {
            this.commentIds = commentIds;
        }

        public String getRawUrl() {
            return rawUrl;
        }

        public void setRawUrl(String rawUrl) {
            this.rawUrl = rawUrl;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Long> getPartIds() {
            return partIds;
        }

        public void setPartIds(List<Long> partIds) {
            this.partIds = partIds;
        }

        public Integer getDescendants() {
            return descendants;
        }

        public void setDescendants(Integer descendants) {
            this.descendants = descendants;
        }
    }

    // the inner real item
    private Item item;
    // the id of item
    private Integer id;
    // the order of the item
    private Integer order;

    public HackerNewsItem(Integer id, Integer order) {
        this.id = id;
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
