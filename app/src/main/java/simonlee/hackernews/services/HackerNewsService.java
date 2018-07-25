package simonlee.hackernews.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import simonlee.hackernews.models.HackerNewsItem;

public interface HackerNewsService {

    @GET("topstories.json")
    Observable<List<Integer>> fetchTopStories();

    @GET("showstories.json")
    Observable<List<Integer>> fetchShowStories();

    @GET("askstories.json")
    Observable<List<Integer>> fetchAskStories();

    @GET("neweststories.json")
    Observable<List<Integer>> fetchNewestStories();

    @GET("beststories.json")
    Observable<List<Integer>> fetchBestStories();

    @GET("item/{id}.json")
    Observable<HackerNewsItem.Item> fetchItem(@Path("id") Integer id);

}
