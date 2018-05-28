package com.wjwinter.mypopularmovies.data;

public class MoviePreferences {

    //Set the preferred language type
    //Defaults to english, but this is where it can be changed
    public static final String PREF_LANG = "en-us";

    //Append this to the URL to get the configuration of the json data
    public static final String CONFIG_STRING = "configuration";

    //Append the URL with this string to get the movie data
    public static final String MOVIE_DATA_STRING = "discover/movie";

    //Append the url with sort by
    public static final String SORT_ORDER = "sort_by";

    //Append the URL for the most popular movies
    public static final String POPULAR_MOVIE_PATH = "movie/popular";

    //Append the URL for the top rated app
    public static final String TOP_RATED_PATH = "movie/top_rated";

    //Create a static string to get the movie db URL
    public static final String MOVIE_DB_URL = "https://api.themoviedb.org/3/";

    //Set a query for api access
    public static final String API_QUERY = "api_key";

    //My API key
    /**
     *
     *   ********************************************************
     *   *                                                      *
     *   *  IMPORTANT Remove this before submitting to get hub  *
     *   *                                                      *
     *   ********************************************************
     * **/
    public static String API_KEY = "7d041a0376829121133fcdc6502e8d70";

    //The movie image url
    public static final String MOVIE_IMAGE_URL = "http://image.tmdb.org/t/p/";

    // The file size of the image to get from the movie db
    public static final String IMAGE_FILE_SIZE = "w500";
}
