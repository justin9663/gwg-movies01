package com.wjwinter.mypopularmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wjwinter.mypopularmovies.R;
import com.wjwinter.mypopularmovies.data.MoviePreferences;
import com.wjwinter.mypopularmovies.modal.Movie;

import java.util.List;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.MoviePosterAdapterViewHolder> {

    /**
     * Variables
     */
//    Used for the images of the poster URLs for testing
    private String[] posterUrls;
    private List<Movie> mMovies;
    private float[] userRatings;
//    Used to tell picasso what what context to use
    private Activity activity;
    private int screenWidth;

    /**
     * An on-click handler to make it easy for an Activity to interface with
     * the RecyclerView
     */
    private final MoviePosterAdapterOnClickHandler mClickHandler;

    /**
     * The interface that will receive the onClick messages.
     */
    public interface MoviePosterAdapterOnClickHandler {
        void onClick(Movie clickedMovie);
    }

    /**
     * Used to create a MoviePosterAdapter, the constructor
     */
    public MoviePosterAdapter(MoviePosterAdapterOnClickHandler clickHandler, Activity activity) {
        mClickHandler = clickHandler;
        this.activity = activity;
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
    }

    /**
     * Cache of the children views for the movie posters
     */
    public class MoviePosterAdapterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
//        For Testing using a TextView
//        public final TextView mPosterTextView;

//        For the ImageView
        public final ImageView mPosterImageView;

        public MoviePosterAdapterViewHolder(View view) {
            super(view);
//            mPosterTextView = view.findViewById(R.id.poster_url_tv);
            mPosterImageView = view.findViewById(R.id.poster_iv);
            view.setOnClickListener(this);
        }

        /**
         * Used to tell what view was clicked on
         **/
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Movie clickedMovie = mMovies.get(adapterPosition);
            mClickHandler.onClick(clickedMovie);

//            Used a string for testing and debugging
//            String posterUrl = posterUrls[adapterPosition];
//            mClickHandler.onClick(posterUrl);
        }
    }

    /**
     * This method gets called when a new view is needed
     *
     */
    @Override
    public MoviePosterAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.poster_images;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MoviePosterAdapterViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position.
     */
    @Override
    public void onBindViewHolder(MoviePosterAdapterViewHolder moviePosterAdapterViewHolder, int position) {
        String urlForThisPoster = MoviePreferences.MOVIE_IMAGE_URL +
                MoviePreferences.IMAGE_FILE_SIZE + posterUrls[position];
        Picasso.with(activity)
                .load(urlForThisPoster)
                .into(moviePosterAdapterViewHolder.mPosterImageView);

//        Used for testing using an text view to display the urls for the images
//        moviePosterAdapterViewHolder.mPosterTextView.setText(urlForThisPoster);
    }

    /**
     * This method is used to get the total number of movie posters there are
     */
    @Override
    public int getItemCount() {
        if (posterUrls == null) return 0;
        return posterUrls.length;
    }

    /**
     * This method is used to set the movie urls forecast on the adapter
     */
    public void setMovieDetails(List<Movie> movies) {
        this.mMovies = movies;
//        Used to get the movie urls
        posterUrls = getMovieUrls(movies);
        userRatings = getMovieRatings(movies);
        notifyDataSetChanged();
    }

    /**
     * This method is used to get the urls out of the movie objects
     * Mainly used for testing purposes.
     */

    private String[] getMovieUrls(List<Movie> movies) {
        int i = 0;
        String[] movieUrls = new String[movies.size()];
        for (Movie movie : movies) {
            movieUrls[i] = movie.getMoviePosterURL();
            i++;
        }
        return movieUrls;
    }
    private float[] getMovieRatings(List<Movie> movies) {
        float[] movieRatings = new float[movies.size()];
        int i = 0;
        for (Movie movie : movies) {
            movieRatings[i] = Float.valueOf(movie.getUserRating());
            i++;
        }
        return movieRatings;
    }
}