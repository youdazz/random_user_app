package com.example.randomuser.ui;

import com.example.randomuser.network.NetworkResponseHandler;
import com.example.randomuser.network.UserRetrofitService;
import com.example.randomuser.model.RandomUserResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private final UserRetrofitService userRetrofitService;

    @Inject
    public UserRepository(UserRetrofitService userRetrofitService){
        this.userRetrofitService = userRetrofitService;
    }

    public void getHundredUsers(NetworkResponseHandler<RandomUserResponse> responseHandler){
        Call<RandomUserResponse> call = userRetrofitService.getHundredUsers();
        call.enqueue(new Callback<RandomUserResponse>() {
            @Override
            public void onResponse(Call<RandomUserResponse> call, Response<RandomUserResponse> response) {
                if (response.isSuccessful()){
                    responseHandler.onResponse(response.body());
                } else {
                    responseHandler.onError(response.code(), response.message());
                }
            }

            @Override
            public void onFailure(Call<RandomUserResponse> call, Throwable t) {
                responseHandler.onFailed(t);
            }
        });
    }
}
