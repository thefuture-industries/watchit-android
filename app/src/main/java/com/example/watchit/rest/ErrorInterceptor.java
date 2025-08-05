package com.example.watchit.rest;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class ErrorInterceptor implements Interceptor {
    private final Context context;

    public ErrorInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        if (!response.isSuccessful()) {
            String errorMessage = "error on the server";

            try {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    String responseString = responseBody.string();

                    org.json.JSONObject jsonObject = new org.json.JSONObject(responseString);
                    errorMessage = jsonObject.optString("message", errorMessage);
                }
            } catch (org.json.JSONException ex) {
                System.out.println(ex.getMessage());
            }

            final String finalErrorMessage = errorMessage;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, finalErrorMessage, Toast.LENGTH_LONG).show();
                }
            });

            // logger
        }

        return response;
    }
}
