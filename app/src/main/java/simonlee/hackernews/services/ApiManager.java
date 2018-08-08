package simonlee.hackernews.services;


public interface ApiManager {

    /**
     * getHackerNewsServices returns the service that communicates with the backend
     * @return the service
     */
    HackerNewsService getHackerNewsServices();
}
