package com.example.randomuser.network;

import com.example.randomuser.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Singleton
    @Provides
    public UserRetrofitService provideUserRetrofitService() {
        return retrofit.create(UserRetrofitService.class);
    }
}
