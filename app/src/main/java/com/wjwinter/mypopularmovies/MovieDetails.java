package com.wjwinter.mypopularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wjwinter.mypopularmovies.data.MoviePreferences;

public class MovieDetails extends AppCompatActivity {

    private ImageView moviePosterIv;
    private TextView movieTitleTv;
    private TextView releaseDateTv;
    private TextView userRatingTv;
    private TextView descriptionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieTitleTv = findViewById(R.id.movie_title_tv);
        releaseDateTv = findViewById(R.id.release_date_tv);
        userRatingTv = findViewById(R.id.rating_tv);
        descriptionTv = findViewById(R.id.description_tv);
        moviePosterIv = findViewById(R.id.poster_detail_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }
        movieTitleTv.setText(intent.getStringExtra("movie_title"));
        releaseDateTv.setText(intent.getStringExtra("release_date"));
        userRatingTv.setText(intent.getStringExtra("user_rating"));
        descriptionTv.setText(intent.getStringExtra("movie_desc"));

        Picasso.with(this)
                .load(MoviePreferences.MOVIE_IMAGE_URL +
                        MoviePreferences.IMAGE_FILE_SIZE +
                        intent.getStringExtra("poster_url"))
                .into(moviePosterIv);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, "Movie Data not available", Toast.LENGTH_LONG).show();
    }
}
