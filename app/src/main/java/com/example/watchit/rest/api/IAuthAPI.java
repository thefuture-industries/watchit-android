package com.example.watchit.rest.api;

import com.example.watchit.models.auth.LoginModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthAPI {
    // login || create
    @POST("auth/create")
    Call<String> login(@Body LoginModel body);
}
