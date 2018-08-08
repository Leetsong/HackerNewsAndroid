package simonlee.hackernews.data;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import io.reactivex.Observable;
import simonlee.hackernews.models.HackerNewsItem;

public interface ItemManager {

    // the story type
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
        STORY_TYPE_TOP,
        STORY_TYPE_BEST,
        STORY_TYPE_NEWEST,
        STORY_TYPE_ASK,
        STORY_TYPE_SHOW
    })
    @interface StoryType {}
    String STORY_TYPE_TOP = "Top";
    String STORY_TYPE_BEST = "Best";
    String STORY_TYPE_NEWEST = "Newest";
    String STORY_TYPE_ASK = "Ask";
    String STORY_TYPE_SHOW = "Show";

    /**
     * getStories gets the typed empty stories
     * @param type type of the stories
     * @return     an observable of stories
     */
    Observable<List<HackerNewsItem>> getStories(@StoryType String type);

    /**
     * getItem gets a specific item
     * @param id id of the item
     * @return   a full item
     */
    Observable<? extends HackerNewsItem.Item> getItem(Integer id);
}
