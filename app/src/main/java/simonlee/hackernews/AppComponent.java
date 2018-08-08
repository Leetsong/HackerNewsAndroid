package simonlee.hackernews;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    // TODO - put all exact injectable object here
    void inject(Object object);
    void inject(StoryListFragment fragment);
}