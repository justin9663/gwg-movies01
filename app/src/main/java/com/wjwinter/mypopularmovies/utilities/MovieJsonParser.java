package com.wjwinter.mypopularmovies.utilities;


import android.content.Context;

import com.wjwinter.mypopularmovies.modal.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Utility used to parse the Movie DB Json data
 */
public class MovieJsonParser {

    public static ArrayList<Movie> getMovieStringsFromJson(Context context, String movieJsonStr)
            throws JSONException {

        final String MOVIE_LIST = "results";

        //String array to hold all of the movie data
        //Changed from string array to movie array, and that was causing errors.
        //Changed this from an array to an array list of movies
        ArrayList<Movie> parsedMovieData = new ArrayList<>();

        JSONObject movieJsonObj = new JSONObject(movieJsonStr);

        //TODO: Will want to do some error checking here to see if there are any errors with the data

        JSONArray movieArray = movieJsonObj.getJSONArray(MOVIE_LIST);

        //Don't need to set the size with an array list
//        parsedMovieData = new Movie[movieArray.length()];


        for (int i = 0; i < movieArray.length(); i++) {

            Movie movieDetails = new Movie();
            int movieId;
            String title;
            String moviePosterURL;
            String overview;
            String releaseDate;

            JSONObject movie = movieArray.getJSONObject(i);

            movieId = movie.getInt("id");
            title = movie.getString("title");
            moviePosterURL = movie.getString("poster_path");
            overview = movie.getString("overview");
            releaseDate = movie.getString("release_date");

            movieDetails.setMovieId(movieId);
            movieDetails.setTitle(title);
            movieDetails.setMoviePosterURL(moviePosterURL);
            movieDetails.setOverview(overview);
            movieDetails.setReleaseDate(releaseDate);

            //This is adding only the first item to the array. This is why you made a
            // multi dimen array, so you could set different items.
            // Perhaps creating a movie array??
            parsedMovieData.add(i, movieDetails);

        }
        return parsedMovieData;

    }

}
