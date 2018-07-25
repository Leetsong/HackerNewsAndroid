package simonlee.hackernews.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import simonlee.hackernews.R;
import simonlee.hackernews.models.HackerNewsItem;
import simonlee.hackernews.utils.DateUtil;

public class HackerNewsItemAdapter
        extends BaseQuickAdapter<HackerNewsItem, BaseViewHolder> {

    public HackerNewsItemAdapter(List<HackerNewsItem> itemList) {
        super(R.layout.item_item, itemList);
    }

    @Override
    protected void convert(BaseViewHolder helper, HackerNewsItem item) {
        HackerNewsItem.Item hackerNewsItem = item.getItem();
        helper.setText(R.id.tv_order, "" + helper.getAdapterPosition());
        if (null == hackerNewsItem) {
            // loading
            helper.getView(R.id.ll_right_body).setBackgroundResource(R.color.grey100);
            helper.setText(R.id.tv_score, "")
                    .setText(R.id.tv_title, "")
                    .setText(R.id.tv_link, "")
                    .setText(R.id.tv_time_author, "")
                    .setText(R.id.tv_nr_comment, "");
        } else {
            // loaded
            helper.getView(R.id.ll_right_body).setBackgroundResource(R.color.itemBodyBackground);
            helper.setText(R.id.tv_score, "" + hackerNewsItem.getScore())
                .setText(R.id.tv_title, hackerNewsItem.getTitle())
                .setText(R.id.tv_link, hackerNewsItem.getRawUrl())
                .setText(R.id.tv_time_author,
                        DateUtil.difference(System.currentTimeMillis(), hackerNewsItem.getCreationTime()))
                .setText(R.id.tv_nr_comment,
                        null == hackerNewsItem.getCommentIds()
                                ? "0"
                                : "" + hackerNewsItem.getCommentIds().size());
        }
    }
}
