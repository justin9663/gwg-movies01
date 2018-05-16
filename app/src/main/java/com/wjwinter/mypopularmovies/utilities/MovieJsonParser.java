package com.wjwinter.mypopularmovies.utilities;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Utility used to parse the Movie DB Json data
 */
public class MovieJsonParser {

    public static String[][] getMovieStringsFromJson(Context context, String movieJsonStr)
            throws JSONException {

        final String MOVIE_LIST = "results";

        //String array to hold all of the movie data
        String[][] parsedMovieData = null;

        JSONObject movieJsonObj = new JSONObject(movieJsonStr);

        //TODO: Will want to do some error checking here to see if there are any errors with the data

        JSONArray movieArray = movieJsonObj.getJSONArray(MOVIE_LIST);

        parsedMovieData = new String[movieArray.length()][];


        for (int i = 0; i < movieArray.length(); i++) {

            String[] movieDetails = new String[5];
            String movieId;
            String title;
            String moviePosterURL;
            String overview;
            String releaseDate;

            JSONObject movie = movieArray.getJSONObject(i);

            movieId = movie.getString("id");
            title = movie.getString("title");
            moviePosterURL = movie.getString("poster_path");
            overview = movie.getString("overview");
            releaseDate = movie.getString("release_date");

            movieDetails[0] = movieId;
            movieDetails[1] = title;
            movieDetails[2] = moviePosterURL;
            movieDetails[3] = overview;
            movieDetails[4] = releaseDate;

            parsedMovieData[i] = movieDetails;

        }
        return parsedMovieData;

    }

}
