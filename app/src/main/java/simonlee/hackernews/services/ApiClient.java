package simonlee.hackernews.services;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    // Hacker News proxy host
    public static final String SERVICE_HOST = "http://45.76.211.58";
    // Hacker News proxy port
    public static final String SERVICE_PORT = "8080";
    // Hacker News proxy base path
    public static final String SERVICE_BASE_PATH = "v0";
    // Hacker News proxy base url
    public static final String SERVICE_BASE_URL =
            SERVICE_HOST + ":" + SERVICE_PORT + "/" + SERVICE_BASE_PATH + "/";

    // the retrofit client - the single instance
    private static Retrofit retrofitClient;

    public static Retrofit getRetrofitClient() {
        if (null == retrofitClient) {
            retrofitClient = createRetrofitClient();
        }
        return retrofitClient;
    }

    public static HackerNewsService getHackerNewsServices() {
        return getRetrofitClient().create(HackerNewsService.class);
    }

    private static Retrofit createRetrofitClient() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(SERVICE_BASE_URL)
                .build();
    }

    // Singleton
    private ApiClient() {}
}
