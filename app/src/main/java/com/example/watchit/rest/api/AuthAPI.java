package com.example.watchit.rest.api;

import android.content.Context;
import com.example.watchit.models.auth.LoginModel;
import com.example.watchit.rest.ApiClient;
import com.example.watchit.rest.RetrofitCallback;

public class AuthAPI {
    private final IAuthAPI authAPI;

    public AuthAPI(Context context) {
        authAPI = ApiClient.getAuthApi(context);
    }

    // login || create
    public void basicLogin(LoginModel body, ApiCallback<String> callback) {
        authAPI.login(body).enqueue(new RetrofitCallback<>(callback));
    }
}
