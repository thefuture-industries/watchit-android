package com.example.watchit.rest;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.example.watchit.constants.LoggingTags;
import com.example.watchit.constants.PreferenceKeys;
import com.example.watchit.logging.Logging;
import com.example.watchit.utils.PreferencesManager;
import okhttp3.Interceptor;
import okhttp3.Request;
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
        okhttp3.Request origRequest = chain.request();

        // get authToken
        PreferencesManager prefs = new PreferencesManager(context);
        String authToken = prefs.getString(PreferenceKeys.AUTH_TOKEN);

        okhttp3.Request.Builder reqBuilder = origRequest.newBuilder();
        if (authToken != null && !authToken.isEmpty()) {
            reqBuilder.addHeader("Authorization", "Bearer " + authToken);
        }

        okhttp3.Request reqWithAuth = reqBuilder.build();

        // send request to server
        Response response = chain.proceed(reqWithAuth);
        if (!response.isSuccessful()) {
            String errorMessage = "error on the server";

            try {
                ResponseBody responseBody = response.peekBody(Long.MAX_VALUE);
                if (responseBody != null) {
                    String responseString = responseBody.string();

                    org.json.JSONObject jsonObject = new org.json.JSONObject(responseString);
                    errorMessage = jsonObject.optString("message", errorMessage);
                }
            } catch (org.json.JSONException ex) {
                Logging.error(LoggingTags.JSON_PARSE_ERROR, ex.getMessage(), ex);
            }

            final String finalErrorMessage = errorMessage;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, finalErrorMessage, Toast.LENGTH_LONG).show();
                }
            });

            Logging.error(LoggingTags.SERVER_RESPONSE_ERROR, finalErrorMessage, null);
        }

        return response;
    }
}
