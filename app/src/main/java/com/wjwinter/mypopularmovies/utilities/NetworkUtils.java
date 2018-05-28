package com.wjwinter.mypopularmovies.utilities;

import android.net.Uri;
import android.util.Log;

import com.wjwinter.mypopularmovies.data.MoviePreferences;
import com.wjwinter.mypopularmovies.modal.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class NetworkUtils {

    //Set the name of this class to its base name for a tag
    private static final String TAG = NetworkUtils.class.getSimpleName();

    //Create a static string to get the movie db URL
    private static final String MOVIE_DB_URL = "https://api.themoviedb.org/3/";

    //Set a query for api access
    private static final String API_QUERY = "api_key";

    //My API key
    /**
     *
     *   ********************************************************
     *   *                                                      *
     *   *  IMPORTANT Remove this before submitting to get hub  *
     *   *                                                      *
     *   ********************************************************
     * **/
    private static String API_KEY = "7d041a0376829121133fcdc6502e8d70";

    public static URL buildUrl (String directory) {
        Uri buildUri = Uri.parse(MOVIE_DB_URL).buildUpon()
                .appendEncodedPath(directory)
                .appendQueryParameter(API_QUERY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URL: " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static Movie parseMovieJsonData(String json) throws JSONException {

        //Movie object that will be returned from the parsed json data
        Movie movie = new Movie();

        JSONObject jsonObject = new JSONObject(json);

        ArrayList<String> movies = new ArrayList<>();

        JSONArray jsonMovieList = jsonObject.getJSONArray("results");

        if (jsonMovieList.length() != 0) {

            for (int i = 0; i < 20; i++) {
                movies.add(jsonMovieList.get(i).toString());
            }
        }


        return movie;

    }
}
