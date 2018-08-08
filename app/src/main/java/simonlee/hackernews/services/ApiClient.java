package simonlee.hackernews.services;


import javax.inject.Singleton;

import retrofit2.Retrofit;


@Singleton
public class ApiClient implements ApiManager {

    // the retrofit client
    private Retrofit retrofit;

    public ApiClient(Retrofit retrofit) { this.retrofit = retrofit; }

    public HackerNewsService getHackerNewsServices() {
        return retrofit.create(HackerNewsService.class);
    }
}
