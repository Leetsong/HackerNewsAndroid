package simonlee.hackernews;

import simonlee.hackernews.data.ItemManager;

public class NewestStoriesFragment extends StoryListFragment {

    // TODO - HTTP 401 Unauthorized

    public static NewestStoriesFragment newFragment() {
        return new NewestStoriesFragment();
    }

    @Override
    public String getDisplayedTitle() {
        return AppContext.getAppContext().getString(R.string.tb_title_newest_stories);
    }

    @Override
    protected String getStoryType() {
        return ItemManager.STORY_TYPE_NEWEST;
    }
}
