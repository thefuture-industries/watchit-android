package com.example.watchit.rest;

import android.content.Context;
import com.example.watchit.BuildConfig;
import com.example.watchit.rest.api.IAuthAPI;
import com.example.watchit.rest.api.IFavouriteAPI;
import com.example.watchit.rest.api.IMovieAPI;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(Context context) {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new ErrorInterceptor(context)).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(String.format("%s://%s:%s", BuildConfig.SERVER_PROTO, BuildConfig.SERVER_ADDR, BuildConfig.SERVER_PORT))
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static IAuthAPI getAuthApi(Context context) {
        return getRetrofit(context).create(IAuthAPI.class);
    }

    public static IMovieAPI getMovieApi(Context context) {
        return getRetrofit(context).create(IMovieAPI.class);
    }

    public static IFavouriteAPI getFavouriteApi(Context context) {
        return getRetrofit(context).create(IFavouriteAPI.class);
    }
}
