package simonlee.hackernews.data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import simonlee.hackernews.models.HackerNewsItem;
import simonlee.hackernews.services.ApiClient;
import simonlee.hackernews.services.HackerNewsService;

public class HackerNewsClient implements ItemManager {

    @Override
    public Observable<List<HackerNewsItem>> getStories(@StoryType String type) {
        HackerNewsService hackerNewsService = ApiClient.getHackerNewsServices();
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
        HackerNewsService hackerNewsService = ApiClient.getHackerNewsServices();
        return hackerNewsService
                .fetchItem(id)
                .subscribeOn(Schedulers.io());
    }
}
