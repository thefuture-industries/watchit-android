package com.example.watchit.models.favourite;

import com.google.gson.annotations.SerializedName;

public class FavouriteModel {
    @SerializedName("id")
    private int id;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("user_uuid")
    private String userUuid;

    @SerializedName("movie_id")
    private int movieId;

    @SerializedName("movie_poster")
    private String moviePoster;

    public int getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMoviePoster() {
        return moviePoster;
    }
}
