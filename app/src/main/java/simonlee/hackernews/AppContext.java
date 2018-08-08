package simonlee.hackernews;

import android.app.Application;


public class AppContext extends Application {

    // the Context
    private static AppContext appContext;
    // the Component
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static AppContext getAppContext() {
        return appContext;
    }
}
