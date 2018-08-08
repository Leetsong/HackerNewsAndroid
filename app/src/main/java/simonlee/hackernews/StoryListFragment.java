package simonlee.hackernews;


import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import simonlee.hackernews.adapters.HackerNewsItemAdapter;
import simonlee.hackernews.data.ItemManager;
import simonlee.hackernews.models.HackerNewsItem;

public abstract class StoryListFragment extends ListFragment<HackerNewsItem>
        implements BaseQuickAdapter.OnItemClickListener, TitledInterface {

    // the app context
    @Inject AppContext appContext;
    // the item manager
    @Inject ItemManager itemManager;

    // the cache
    private final int PAGE = 15;
    List<HackerNewsItem> cachedList = new LinkedList<>();

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        // TODO - Item Activity
        Toast.makeText(appContext, "Clicked at " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected BaseQuickAdapter onCreateAdapter() {
        BaseQuickAdapter adapter = new HackerNewsItemAdapter(null);
        adapter.setOnItemClickListener(this);
        return adapter;
    }

    @Override
    protected void onRefresh(BaseQuickAdapter adapter, SwipeRefreshLayout swipeRefreshLayout) {
        // start refreshing
        swipeRefreshLayout.setRefreshing(true);
        itemManager.getStories(getStoryType())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onRefreshComplete, this::onRefreshError);
    }

    @Override
    protected void onLoadMore(BaseQuickAdapter adapter, SwipeRefreshLayout swipeRefreshLayout) {
        if (null != cachedList && cachedList.size() > 0) {
            // disable refreshing
            swipeRefreshLayout.setRefreshing(false);

            // add a new sub list from cached list to render
            List<HackerNewsItem> toRenderedList = cachedList.subList(0, PAGE);
            // TODO - java.util.ConcurrentModificationException
            addItemList(toRenderedList);
            toRenderedList.forEach(this::getItem);

            // shrink cached list
            cachedList = cachedList.subList(PAGE, cachedList.size());

            // enable refreshing
            swipeRefreshLayout.setRefreshing(true);

            // load more complete
            adapter.loadMoreComplete();
        } else {
            adapter.loadMoreEnd(true);
        }
    }

    @Override
    protected void onRefreshComplete(List<HackerNewsItem> itemList) {
        // setup a sub list to render
        List<HackerNewsItem> toRenderedList = itemList.subList(0, PAGE);
        setItemList(toRenderedList);
        getRefresher().setRefreshing(false);
        toRenderedList.forEach(this::getItem);

        // setup cached list
        cachedList = itemList.subList(PAGE, itemList.size());
    }

    @Override
    protected void onRefreshError(Throwable throwable) {
        super.onRefreshError(throwable);
        // TODO - refresh error
    }

    @ItemManager.StoryType
    protected abstract String getStoryType();

    private void getItem(HackerNewsItem item) {
        itemManager.getItem(item.getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    item.setItem(it);
                    synchronized (adapter) {
                        adapter.setData(item.getOrder(), item);
                    }
                },
                // TODO - loading error
                error -> error.printStackTrace());
    }
}
