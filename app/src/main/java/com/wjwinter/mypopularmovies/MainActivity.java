package com.wjwinter.mypopularmovies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wjwinter.mypopularmovies.data.MoviePreferences;
import com.wjwinter.mypopularmovies.modal.Movie;
import com.wjwinter.mypopularmovies.utilities.MovieJsonParser;
import com.wjwinter.mypopularmovies.utilities.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements MoviePosterAdapter.MoviePosterAdapterOnClickHandler{

    // Variables
    List<Movie> myMovies;
    String[] movieUrls;
    TextView errorMessage;


    private RecyclerView mRecyclerView;
    private MoviePosterAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupSharedPreferences();

        //Get a reference to the recycler view
        mRecyclerView = findViewById(R.id.movie_recycler_view);

        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        mMovieAdapter = new MoviePosterAdapter(this, this);

        mRecyclerView.setAdapter(mMovieAdapter);

        // Call the load movie method to get the movie data
        loadMovieData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movies_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.movie_settings) {
//            Intent intent = new Intent(this, SettingsActivity.class);
//            startActivity(intent);
//            return true;
//        }
        if (id == R.id.sort_popular) {
            Toast.makeText(this, "Most Popular", Toast.LENGTH_LONG).show();
            sortMoviesPopular();
        }

        if (id == R.id.sort_rating) {
            Toast.makeText(this, "Top Rated", Toast.LENGTH_LONG).show();
            sortMoviesRating();
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isNetworkConnected () {
        ConnectivityManager cm = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);


    }

    private void sortMoviesPopular()  {
        errorMessage = findViewById(R.id.error_message);
        errorMessage.setVisibility(View.GONE);
        String[] moviePathArray = {MoviePreferences.POPULAR_MOVIE_PATH};

        new GetMovieJsonTask().execute(moviePathArray);
    }

    private void sortMoviesRating()  {
        errorMessage = findViewById(R.id.error_message);
        errorMessage.setVisibility(View.GONE);
        String[] moviePathArray = {MoviePreferences.TOP_RATED_PATH};

        new GetMovieJsonTask().execute(moviePathArray);
    }

    // Method used to load the data from the Async task
    private void loadMovieData() {
        errorMessage = findViewById(R.id.error_message);
        if(isNetworkConnected()) {
            errorMessage.setVisibility(View.GONE);
            // String array of the possible paths that will be used to get movie data
            String[] moviePathArray = {MoviePreferences.MOVIE_DATA_STRING};

            // The Async task to load the movie data
            new GetMovieJsonTask().execute(moviePathArray);
        } else{

            errorMessage.setText("There needs to be an internet conncetion for the movie app to work.");
        }

    }

    @Override
    public void onClick(Movie clickedMovie) {
        Intent intent = new Intent(this, MovieDetails.class);
        intent.putExtra("movie_title", clickedMovie.getTitle());
        intent.putExtra("release_date", clickedMovie.getReleaseDate());
        intent.putExtra("user_rating", clickedMovie.getUserRating());
        intent.putExtra("poster_url", clickedMovie.getMoviePosterURL());
        intent.putExtra("movie_desc", clickedMovie.getOverview());
        startActivity(intent);

//        Used for testing what was clicked on when debugging
//        Context context =  this;
//        Toast.makeText(context, moviePosterUrl, Toast.LENGTH_LONG).show();
    }

    private void showMoviePosterUrls() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    /** AsyncTask used to load the data from the movie db
    // from https://developer.android.com/reference/android/os/AsyncTask
    // The three types used by an asynchronous task are the following:
    //
    //      Params, the type of the parameters sent to the task upon execution.
    //      Progress, the type of the progress units published during the background computation.
    //      Result, the type of the result of the background computation.

    // My method params are a String array for the url params, void for the progress(currently)
    // and a List as the return type
     **/
    private class GetMovieJsonTask extends AsyncTask<String[], Void, List<Movie>> {

        protected void onPreExecute() {
            //Enter the details of what will happen if the load takes a while
            //Not required for this version, but if I have time, I will try and
            //integrate this with a loader graphic, etc
        }

        @Override
        protected ArrayList<Movie> doInBackground(String[]... params) {
            // moviePath is the path of the url to follow
            String[] moviePath = params[0];
            //Pass this variable to the NetworkUtils to build the url
            URL movieDbUrl = NetworkUtils.buildUrl(moviePath[0]);

            try {

                // Get the response from the url passed in
                String jsonMovieResponse = NetworkUtils.
                        getResponseFromHttpUrl(movieDbUrl);

                // Get the movie data from the json parser using the response
                ArrayList<Movie> jsonMovieData = MovieJsonParser
                        .getMovieStringsFromJson(MainActivity.this, jsonMovieResponse);

                return jsonMovieData;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            // A loop to add all the movies to the myMovie list
            if (movies.size() != 0){
                showMoviePosterUrls();
                mMovieAdapter.setMovieDetails(movies);
            }
//            int i = 0;
//            for (Movie movie : movies) {
//                myMovies.add(i, movie);
//                i++;
//            }
        }
    }
}