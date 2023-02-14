package com.example.randomuser.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.randomuser.model.RandomUserResponse;
import com.example.randomuser.model.User;
import com.example.randomuser.model.UserApiModel;
import com.example.randomuser.network.NetworkResponseHandler;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ListViewModel {
    private final UserRepository userRepository;
    private final MutableLiveData<List<User>> listUserMD = new MutableLiveData<>();
    private final MutableLiveData<String> errorMD = new MutableLiveData<>();

    @Inject
    public ListViewModel(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void getHundredUsers(){
        userRepository.getHundredUsers(new NetworkResponseHandler<RandomUserResponse>() {
            @Override
            public void onResponse(RandomUserResponse response) {
                List<User> users = parseApiModel(response.results);
                listUserMD.setValue(users);
            }

            @Override
            public void onError(int errorCode, String message) {
                String msg = String.format("error code %s, message: %s",errorCode, message);
                errorMD.setValue(msg);
            }

            @Override
            public void onFailed(Throwable e) {
                errorMD.setValue("Fallo de conexión con el servidor");
            }
        });
    }

    private List<User> parseApiModel(List<UserApiModel> results) {
        List<User> users = new ArrayList<>();
        for (UserApiModel apiModel : results) {
            users.add(new User(apiModel));
        }
        return users;
    }

    public LiveData<String> getErrorMD() {
        return errorMD;
    }

    public void eraseError(){
        errorMD.setValue(null);
    }

    public LiveData<List<User>> getListUserMD() {
        return listUserMD;
    }
}
