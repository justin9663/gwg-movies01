package com.wjwinter.mypopularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wjwinter.mypopularmovies.R;
import com.wjwinter.mypopularmovies.data.MoviePreferences;
import com.wjwinter.mypopularmovies.modal.Movie;

import java.util.List;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.MoviePosterAdapterViewHolder> {

    /**
     * Variable to hold all of the movie URLs.
     */
    private String[] posterUrls;

    /**
     * An on-click handler to make it easy for an Activity to interface with
     * the RecyclerView
     */
    private final MoviePosterAdapterOnClickHandler mClickHandler;

    /**
     * The interface that will receive the onClick messages.
     */
    public interface MoviePosterAdapterOnClickHandler {
        void onClick(String moviePosterUrl);
    }

    /**
     * Used to create a MoviePosterAdapter, the constructor
     */
    public MoviePosterAdapter(MoviePosterAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    /**
     * Cache of the children views for the movie posters
     */
    public class MoviePosterAdapterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
//        For Testing using a TextView
        public final TextView mPosterTextView;

//        For the ImageView
//        public final ImageView mPosterImageView;

        public MoviePosterAdapterViewHolder(View view) {
            super(view);
            mPosterTextView = view.findViewById(R.id.poster_url_tv);
//            mPosterImageView = view.findViewById(R.id.poster_iv);
            view.setOnClickListener(this);
        }

        /**
         * Used to tell what view was clicked on
         **/
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String posterUrl = posterUrls[adapterPosition];
            mClickHandler.onClick(posterUrl);
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

        moviePosterAdapterViewHolder.mPosterTextView.setText(urlForThisPoster);
    }

    /**
     * This method is used to get the total number of movie posters there are
     */
    @Override
    public int getItemCount() {
        if (null == posterUrls) return 0;
        return posterUrls.length;
    }

    /**
     * This method is used to set the movie urls forecast on the adapter
     */
    public void setMovieUrls(List<Movie> myMovies) {
        posterUrls = getMovieUrls(myMovies);
        notifyDataSetChanged();
    }

    /**
     * This method is used to get the urls out of the movie objects
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
}