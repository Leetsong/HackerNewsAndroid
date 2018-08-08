package simonlee.hackernews;

import simonlee.hackernews.data.ItemManager;

public class TopStoriesFragment extends StoryListFragment {

    public static TopStoriesFragment newFragment() {
        return new TopStoriesFragment();
    }

    @Override
    public String getDisplayedTitle() {
        return AppContext.getAppContext().getString(R.string.tb_title_top_stories);
    }

    @Override
    protected String getStoryType() {
        return ItemManager.STORY_TYPE_TOP;
    }
}
