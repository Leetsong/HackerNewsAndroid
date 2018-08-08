package simonlee.hackernews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import simonlee.hackernews.models.HackerNewsItem;

public abstract class ListFragment<T> extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_item_list)
    RecyclerView rvItemList;

    @BindView(R.id.srl_refresher)
    SwipeRefreshLayout srlRefresher;

    // RecyclerView adapter
    protected BaseQuickAdapter adapter;

    // RecyclerView LayoutManager
    protected RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, v);

        // create adapter & layout manager
        adapter = onCreateAdapter();
        layoutManager = onCreateLayoutManager();

        // initialize refreshEvent and loadMoreEvent
        srlRefresher.setColorSchemeResources(R.color.colorPrimary);
        srlRefresher.setOnRefreshListener(this);
        adapter.setOnLoadMoreListener(this, rvItemList);

        // refresh
        onRefresh();

        return v;
    }

    @Override
    public final void onRefresh() {
        onRefresh(adapter, srlRefresher);
    }

    @Override
    public void onLoadMoreRequested() {
        // disable refresher
        srlRefresher.setEnabled(false);
        onLoadMore(adapter, srlRefresher);
    }

    /**
     * getItemList returns the item list to be rendered
     *
     * @return the item list
     */
    public List<T> getItemList() {
        return adapter.getData();
    }

    /**
     * getRecyclerView returns the recycler view
     *
     * @return the recycler view
     */
    public RecyclerView getRecyclerView() {
        return rvItemList;
    }

    /**
     * getAdapter returns the adapter
     *
     * @return the adapter for recycler view
     */
    public BaseQuickAdapter getAdapter() {
        return adapter;
    }

    /**
     * getRefresher returns the refresh layout
     * @return the refresh layout
     */
    public SwipeRefreshLayout getRefresher() { return srlRefresher; }

    /**
     * getLayoutManager returns the layout manager
     *
     * @return the layout manager for recycler view
     */
    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    /**
     * onCreateAdapter creates the adapter for recycler view
     *
     * @return a recycler view adapter
     */
    protected abstract BaseQuickAdapter onCreateAdapter();

    /**
     * onCreateLayoutManager creates the layout manager for recycler view
     *
     * @return a recycler view layout manager
     */
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    /**
     * onRefresh refreshes the recycler view
     *
     * @param adapter
     * @param swipeRefreshLayout
     */
    protected abstract void onRefresh(BaseQuickAdapter adapter, SwipeRefreshLayout swipeRefreshLayout);

    /**
     * onLoadMore loads more items for the recycler view
     *
     * @param adapter
     * @param swipeRefreshLayout
     */
    protected abstract void onLoadMore(BaseQuickAdapter adapter, SwipeRefreshLayout swipeRefreshLayout);

    /**
     * setItemList sets up the recycler view
     * @param itemList the item list to be set
     */
    protected void setItemList(List<HackerNewsItem> itemList) {
        // setup new data
        synchronized (adapter) {
            adapter.setNewData(itemList);
        }
        // setup layout manager
        rvItemList.setLayoutManager(layoutManager);
        // setup adapter
        rvItemList.setAdapter(adapter);
    }

    /**
     * addItems add more items to recycler view
     * @param itemList items to be added
     * @return         length of new data
     */
    protected void addItemList(List<T> itemList) {
        // add data
        synchronized (adapter) {
            adapter.addData(itemList);
        }
    }

    /**
     * onRefreshComplete sets up the recycler view when refreshes successfully
     * @param itemList the item list to be set
     */
    protected void onRefreshComplete(List<HackerNewsItem> itemList) {
        // set up recycler view
        setItemList(itemList);
        // stop refreshing
        srlRefresher.setRefreshing(false);
    }

    /**
     * onRefreshError handles the error when refresh fails
     * @param throwable error thrown
     */
    protected void onRefreshError(Throwable throwable) {
        srlRefresher.setRefreshing(false);
    }
}
