package com.example.watchit.rest;

import com.example.watchit.constants.LoggingTags;
import com.example.watchit.logging.Logging;
import com.example.watchit.rest.api.ApiCallback;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCallback<T> implements Callback<T> {
    private final ApiCallback<T> apiCallback;

    public RetrofitCallback(ApiCallback<T> apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful() && response.body() != null) {
            apiCallback.onSuccess(response.body());
        } else {
            String errorMessage = "error on the server";

            try {
                if (response.errorBody() != null) {
                    String errorJson = response.errorBody().string();
                    JSONObject jsonObject = new JSONObject(errorJson);
                    errorMessage = jsonObject.optString("message", errorMessage);
                }
            } catch (Exception ex) {
                Logging.error(LoggingTags.RETROFIT_CALLBACK_ERROR, ex.getMessage(), ex);
            }

            Logging.error(LoggingTags.RETROFIT_REQUEST_ERROR, errorMessage, null);
            apiCallback.onError(new Exception(errorMessage));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Logging.error(LoggingTags.RETROFIT_FAILURE_ERROR, t.getMessage(), t);
        apiCallback.onError(t);
    }
}
