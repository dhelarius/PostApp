package com.itla.postapp.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.itla.postapp.model.LoginCredentials;
import com.itla.postapp.webservice.LoginClient;

public class LoginViewModel extends ViewModel {

    private LoginClient loginClient;
    private LoginCredentials credentials;

    LoginViewModel(LoginCredentials credentials){
        this.credentials = credentials;
        loginClient = new LoginClient(credentials);
    }

}
