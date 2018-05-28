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

        // Use this to find the list of movies that is in an element called results currently
        final String MOVIE_LIST = "results";

        //String array to hold all of the movie data
        //Changed from string array to movie array, and that was causing errors.
        //Changed this from an array to an array list of movies.

        // Use an ArraList<Movie> to holdall of the movie objects from the parsed json
        ArrayList<Movie> parsedMovieData = new ArrayList<>();

        //Create a jsonObject from the json string data
        JSONObject movieJsonObj = new JSONObject(movieJsonStr);

        // Get the jsonArray out of the jsonObject using th e MOVIE_LIST constant
        JSONArray movieArray = movieJsonObj.getJSONArray(MOVIE_LIST);

        //Create a for loop to walk thru the jsonArray and add the movies to the ArrayList
        for (int i = 0; i < movieArray.length(); i++) {

            // Instantiate the movie object that will be added to the ArrayList
            Movie movieDetails = new Movie();

            //Get each movie from the json movieArray data
            JSONObject movie = movieArray.getJSONObject(i);

            //Assign the parameters to the movie object
            movieDetails.setMovieId(movie.getInt("id"));
            movieDetails.setTitle(movie.getString("title"));
            movieDetails.setMoviePosterURL(movie.getString("poster_path"));
            movieDetails.setOverview(movie.getString("overview"));
            movieDetails.setReleaseDate(movie.getString("release_date"));
            movieDetails.setUserRating(movie.getString("vote_average"));

            //Add the Movie object to the ArrayList
            parsedMovieData.add(i, movieDetails);

        }
        return parsedMovieData;

    }

}
