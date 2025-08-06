package com.example.watchit.rest.api;

public interface ApiCallback<T> {
    void onSuccess(T result);
    void onError(Throwable t);
}
