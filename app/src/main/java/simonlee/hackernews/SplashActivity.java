package simonlee.hackernews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import simonlee.hackernews.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startHackerNewsApp();
        finish();
    }

    private void startHackerNewsApp() {
        Timer timer = new Timer();
        TimerTask delayedTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HNActivity.class));
            }
        };
        timer.schedule(delayedTask, 3000);
    }
}
