package com.example.watchit.rest.api;

import com.example.watchit.models.movie.MovieModel;
import com.example.watchit.models.movie.SearchModel;
import com.example.watchit.models.movie.SuggestModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface IMovieAPI {
    @POST("movies/suggest")
    Call<List<MovieModel>> suggest(@Body SuggestModel body);

    @POST("movies/search")
    Call<List<MovieModel>> search(@Body SearchModel body);

    @GET("movies/image/{image}")
    Call<ResponseBody> image(@Path("image") String image);

    @GET("movies/details/{id}")
    Call<MovieModel> details(@Path("id") int id);
}
