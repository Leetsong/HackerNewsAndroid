package simonlee.hackernews.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HackerNewsService {

    @GET("topstories.json")
    Observable<List<Long>> fetchTopStories();

    @GET("beststories.json")
    Observable<List<Long>> fetchBestStories();

}
