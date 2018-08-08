package simonlee.hackernews;


import simonlee.hackernews.data.ItemManager;

public class BestStoriesFragment extends StoryListFragment {

    public static BestStoriesFragment newFragment() {
        return new BestStoriesFragment();
    }

    @Override
    public String getDisplayedTitle() {
        return AppContext.getAppContext().getString(R.string.tb_title_best_stories);
    }

    @Override
    protected String getStoryType() {
        return ItemManager.STORY_TYPE_BEST;
    }
}
