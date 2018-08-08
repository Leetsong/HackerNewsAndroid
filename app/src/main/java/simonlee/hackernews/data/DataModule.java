package simonlee.hackernews.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import simonlee.hackernews.services.ApiManager;

@Module
public class DataModule {

    @Provides
    @Singleton
    public ItemManager providesItemManager$HackerNewsClient(ApiManager apiManager) {
        return new HackerNewsClient(apiManager);
    }
}
