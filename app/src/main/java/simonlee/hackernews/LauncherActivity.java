package simonlee.hackernews;

import android.os.Bundle;

public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        startHackerNewsApp();
        finish();
    }

    private void startHackerNewsApp() {
        // TODO - the splash things
        ListActivity.open(LauncherActivity.this);
    }
}
