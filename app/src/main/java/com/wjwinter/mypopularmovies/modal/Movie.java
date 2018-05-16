package com.wjwinter.mypopularmovies.modal;

public class Movie {

    // Movie object attributes
    private int movieId;
    private String title;
    private String moviePosterURL;
    private String overview;
    private String userRating;
    private String releaseDate;

    //Empty constructor used for testing and debugging
    public Movie(){

    }

    // Constructor
    public Movie (int id, String title, String posterImage, String overview,
                  String rating, String releaseDate) {
        this.movieId = id;
        this.title = title;
        this.moviePosterURL = posterImage;
        this.overview = overview;
        this.userRating = rating;
        this.releaseDate = releaseDate;
    }

    // Getter and setter methods for all the attributes


    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoviePosterURL() {
        return moviePosterURL;
    }

    public void setMoviePosterURL(String moviePosterURL) {
        this.moviePosterURL = moviePosterURL;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
