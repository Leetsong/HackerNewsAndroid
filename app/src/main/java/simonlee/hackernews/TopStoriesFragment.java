package simonlee.hackernews;

import simonlee.hackernews.data.ItemManager;

public class TopStoriesFragment extends StoryListFragment {

    public static TopStoriesFragment newFragment() {
        return new TopStoriesFragment();
    }

    @Override
    protected String getStoryType() {
        return ItemManager.STORY_TYPE_TOP;
    }

    @Override
    public String getDisplayedTitle() {
        return AppContext.getContext().getString(R.string.tb_title_top_stories);
    }
}
