package com.itla.postapp.ui.viewmodel;

import android.app.Activity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.itla.postapp.model.LoginCredentials;
import com.itla.postapp.model.user.LoginResponse;
import com.itla.postapp.webservice.LoginClient;

public class LoginViewModel extends ViewModel {

    private LoginClient loginClient;
    private MutableLiveData<Boolean> loggedIn = new MutableLiveData<>();

    LoginViewModel(Activity activity, LoginCredentials credentials){
        loginClient = new LoginClient(activity, credentials);
    }

    public LiveData<LoginResponse> login(){
        loggedIn.setValue(true);
        return loginClient.login();
    }

    public LiveData<Boolean> isLoggedIn(){
        return loggedIn;
    }

    public void hasBeenLogged(){
        loggedIn.setValue(false);
    }
}
