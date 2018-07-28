package simonlee.hackernews;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "HNActivity";

    // Any fragment that uses ListActivity as its host must have a
    // static method called newFragment, which returns the created fragment
    public static final String NEW_FRAGMENT_METHOD_NAME = "newFragment";

    @BindView(R.id.dl_root)
    DrawerLayout dlDrawerLayout;

    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;

    @BindView(R.id.nv_navigation)
    NavigationView nvNavigation;

    @BindView(R.id.fl_content_frame)
    FrameLayout flContentFrame;

    /**
     * open starts a HNListActivity
     * @param context start context
     */
    public static void open(Context context) {
        Intent intent = new Intent(context, ListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        // configure the tool bar
        setSupportActionBar(tbToolbar);

        // show drawer
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_menu);
        }

        // set navigation view
        nvNavigation.setCheckedItem(R.id.item_top_stories);
        nvNavigation.setNavigationItemSelectedListener(this);

        // route to index
        routeTo(TopStoriesFragment.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                dlDrawerLayout.openDrawer(GravityCompat.START); break;
            default: break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        dlDrawerLayout.closeDrawers();

        // TODO - replace with new fragment

        nvNavigation.setCheckedItem(item.getItemId());
        switch (item.getItemId()) {
            case R.id.item_top_stories:
                routeTo(TopStoriesFragment.class); break;
            case R.id.item_ask_stories:
                Toast.makeText(this, "ASK", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_show_stories:
                Toast.makeText(this, "SHOW", Toast.LENGTH_SHORT).show(); break;
            case R.id.item_newest_stories:
                Toast.makeText(this, "NEWEST", Toast.LENGTH_SHORT).show(); break;
            case R.id.item_best_stories:
                routeTo(BestStoriesFragment.class); break;
        }

        return false;
    }

    /**
     * getToolbarSubTitle gets the subtitle of tool bar
     * @return subtitle of the tool bar
     */
    protected CharSequence getToolbarSubTitle() {
        return null;
    }

    private void routeTo(Class<? extends StoryListFragment> fragment) {
        try {
            // route to fragment, and set current title/subtitle
            StoryListFragment toFragment =
                    (StoryListFragment) fragment.getMethod(NEW_FRAGMENT_METHOD_NAME)
                            .invoke(fragment);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fl_content_frame, toFragment);
            fragmentTransaction.commit();

            tbToolbar.setTitle(toFragment.getDisplayedTitle());
        } catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
            // TODO - handle exception
            e.printStackTrace();
        }
    }
}
