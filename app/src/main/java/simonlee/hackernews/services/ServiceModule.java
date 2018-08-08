package simonlee.hackernews.services;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ServiceModule {

    public static final String NAMED_HOST = "NAMED_HOST";
    public static final String NAMED_PORT = "NAMED_PORT";
    public static final String NAMED_BASE_PATH = "NAMED_BASE_PATH";
    public static final String NAMED_BASE_URL = "NAMED_BASE_URL";

    // Hacker News proxy host
    @Provides @Named(NAMED_HOST)
    public String provicesHost() {
        return "http://45.76.211.58";
    }

    // Hacker News proxy port
    @Provides @Named(NAMED_PORT)
    public String provicesPort() {
        return "8080";
    }

    // Hacker News proxy base path
    @Provides @Named(NAMED_BASE_PATH)
    public String provicesBasePath() {
        return "v0";
    }

    // Hacker News proxy base url
    @Provides @Named(NAMED_BASE_URL)
    public String provicesBaseUrl(
            @Named(NAMED_HOST) String host,
            @Named(NAMED_PORT) String port,
            @Named(NAMED_BASE_PATH) String basePath) {
        return host + ":" + port + "/" + basePath + "/";
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit(@Named(NAMED_BASE_URL) String baseUrl) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    @Provides
    @Singleton
    public ApiManager providesApiManager$ApiClient(Retrofit retrofit) {
        return new ApiClient(retrofit);
    }
}
