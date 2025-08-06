package com.example.watchit.models.favourite;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class FavouriteAddModel {
    @SerializedName("movie_id")
    private int movieId;

    @SerializedName("movie_poster")
    private String moviePoster;

    public FavouriteAddModel(int movieId, String moviePoster) {
        this.movieId = movieId;
        this.moviePoster = moviePoster;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, moviePoster);
    }
}
