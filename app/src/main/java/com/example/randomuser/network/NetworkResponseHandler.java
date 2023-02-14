package com.example.randomuser.network;

public interface NetworkResponseHandler<T> {
    void onResponse(T response);
    void onError(int errorCode, String message);
    void onFailed(Throwable e);
}
