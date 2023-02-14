package com.example.randomuser.network;

import com.example.randomuser.model.RandomUserResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserRetrofitService {

    @GET("?results=100")
    Call<RandomUserResponse> getHundredUsers();
}
