package simonlee.hackernews;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import simonlee.hackernews.data.DataModule;
import simonlee.hackernews.services.ServiceModule;

@Module(includes = {ServiceModule.class, DataModule.class})
public class AppModule {
    private AppContext appContext;

    public AppModule(AppContext appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    public AppContext providesAppContext() {
        return appContext;
    }
}
