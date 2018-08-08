package simonlee.hackernews.data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import simonlee.hackernews.models.HackerNewsItem;
import simonlee.hackernews.services.ApiManager;
import simonlee.hackernews.services.HackerNewsService;

public class HackerNewsClient implements ItemManager {

    private ApiManager apiManager;

    public HackerNewsClient(ApiManager apiManager) { this.apiManager = apiManager; }

    @Override
    public Observable<List<HackerNewsItem>> getStories(@StoryType String type) {
        HackerNewsService hackerNewsService = apiManager.getHackerNewsServices();
        try {
            String methodName = "fetch" + type + "Stories";
            Method m = hackerNewsService.getClass().getMethod(methodName);
            return ((Observable<List<Integer>>)(m.invoke(hackerNewsService)))
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.computation())
                    .map(ids -> {
                        List<HackerNewsItem> itemList = new ArrayList<>(ids.size());
                        ids.forEach(id -> itemList.add(new HackerNewsItem(id, itemList.size())));
                        return itemList;
                    });
        } catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
            e.printStackTrace();
            return Observable.just(new ArrayList<>());
        }
    }

    @Override
    public Observable<? extends HackerNewsItem.Item> getItem(Integer id) {
        HackerNewsService hackerNewsService = apiManager.getHackerNewsServices();
        return hackerNewsService
                .fetchItem(id)
                .subscribeOn(Schedulers.io());
    }
}
