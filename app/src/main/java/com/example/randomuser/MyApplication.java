package com.example.randomuser;

import android.app.Application;

public class MyApplication extends Application {

    public ApplicationComponent appComponent = DaggerApplicationComponent.create();
}
