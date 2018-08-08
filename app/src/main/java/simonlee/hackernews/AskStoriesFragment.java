package simonlee.hackernews;

import simonlee.hackernews.data.ItemManager;

public class AskStoriesFragment extends StoryListFragment {

    public static AskStoriesFragment newFragment() {
        return new AskStoriesFragment();
    }

    @Override
    public String getDisplayedTitle() {
        return AppContext.getAppContext().getString(R.string.tb_title_ask_stories);
    }

    @Override
    protected String getStoryType() {
        return ItemManager.STORY_TYPE_ASK;
    }
}
