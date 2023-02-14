package com.example.randomuser;

import com.example.randomuser.network.NetworkModule;
import com.example.randomuser.ui.UserListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = NetworkModule.class)
public interface ApplicationComponent {
    void inject(UserListFragment userListFragment);
}
