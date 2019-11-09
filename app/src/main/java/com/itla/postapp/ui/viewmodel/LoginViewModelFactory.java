package com.itla.postapp.ui.viewmodel;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.itla.postapp.model.LoginCredentials;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private final LoginCredentials credentials;
    private final Activity activity;

    public LoginViewModelFactory(Activity activity, LoginCredentials credentials) {
        this.activity = activity;
        this.credentials = credentials;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T) new LoginViewModel(activity, credentials);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
