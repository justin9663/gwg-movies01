package com.wjwinter.mypopularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.wjwinter.mypopularmovies.data.MoviePreferences;
import com.wjwinter.mypopularmovies.modal.Movie;
import com.wjwinter.mypopularmovies.utilities.MovieJsonParser;
import com.wjwinter.mypopularmovies.utilities.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView movieDbUrl;
    Button clickMe;
    String movieJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get a reference to the movie url textview
        //Set the text in the GetMovieJsonTask onPostExecute method
        movieDbUrl = findViewById(R.id.movie_url);

        try {
            Movie movie = NetworkUtils.parseMovieJsonData(movieJson);

        } catch (Exception e) {
            e.printStackTrace();
        }

        loadMovieData();
        //Used for debugging and getting the movie data
//        clickMe = findViewById(R.id.getJson);
//        clickMe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new GetMovieJsonTask().execute(jsonMovieDB);
//            }
//        });
    }

    private void loadMovieData() {
        String[] moviePathArray = {MoviePreferences.MOVIE_DATA_STRING};
        new GetMovieJsonTask().execute(moviePathArray);
    }

    private class GetMovieJsonTask extends AsyncTask<String[], String[], String[]> {


        protected void onPreExecute() {
            //Enter the details of what will happen if the load takes a while
            //Not required for this version, but if I have time, I will try and
            //integrate this with a loader graphic, etc
        }

        @Override
        protected String[] doInBackground(String[]... strings) {
            String[] moviePath = strings[0];
            URL movieDbUrl = NetworkUtils.buildUrl(moviePath[0]);

            try {

                String jsonMovieResponse = NetworkUtils.
                        getResponseFromHttpUrl(movieDbUrl);

                String[][] jsonMovieData = MovieJsonParser
                        .getMovieStringsFromJson(MainActivity.this, jsonMovieResponse);

                return jsonMovieData;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
        }
    }


}