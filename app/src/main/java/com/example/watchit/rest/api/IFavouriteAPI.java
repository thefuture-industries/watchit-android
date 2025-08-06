package com.example.watchit.rest.api;

import com.example.watchit.models.favourite.FavouriteAddModel;
import com.example.watchit.models.favourite.FavouriteModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface IFavouriteAPI {
    // get favourites
    @GET("favourites/")
    Call<List<FavouriteModel>> get();

    // add favourites
    @POST("favourites/")
    Call<String> add(@Body FavouriteAddModel body);
}
