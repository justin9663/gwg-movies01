package com.wjwinter.mypopularmovies.data;

public class MoviePreferences {

    //Set the preferred language type
    //Defaults to english, but this is where it can be changed
    public static final String PREF_LANG = "en-us";

    //Append this to the URL to get the configuration of the json data
    public static final String CONFIG_STRING = "configuration";

    //Append the URL with this string to get the movie data
    public static final String MOVIE_DATA_STRING = "discover/movie";
}
