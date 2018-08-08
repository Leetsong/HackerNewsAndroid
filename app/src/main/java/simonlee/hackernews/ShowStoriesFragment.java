package simonlee.hackernews;

import simonlee.hackernews.data.ItemManager;

public class ShowStoriesFragment extends StoryListFragment {

    public static ShowStoriesFragment newFragment() {
        return new ShowStoriesFragment();
    }

    @Override
    public String getDisplayedTitle() {
        return AppContext.getAppContext().getString(R.string.tb_title_show_stories);
    }

    @Override
    protected String getStoryType() {
        return ItemManager.STORY_TYPE_SHOW;
    }
}
