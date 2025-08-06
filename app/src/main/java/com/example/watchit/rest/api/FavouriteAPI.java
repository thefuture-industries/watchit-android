package com.example.watchit.rest.api;

import android.content.Context;
import com.example.watchit.models.favourite.FavouriteAddModel;
import com.example.watchit.models.favourite.FavouriteModel;
import com.example.watchit.rest.ApiClient;
import com.example.watchit.rest.RetrofitCallback;

import java.util.List;

public class FavouriteAPI {
    private final IFavouriteAPI favouriteAPI;

    public FavouriteAPI(Context context) {
        favouriteAPI = ApiClient.getFavouriteApi(context);
    }

    // get favourites
    public void basicGet(ApiCallback<List<FavouriteModel>> callback) {
        favouriteAPI.get().enqueue(new RetrofitCallback<>(callback));
    }

    // add favourites
    public void basicAdd(FavouriteAddModel body, ApiCallback<String> callback) {
        favouriteAPI.add(body).enqueue(new RetrofitCallback<>(callback));
    }
}
