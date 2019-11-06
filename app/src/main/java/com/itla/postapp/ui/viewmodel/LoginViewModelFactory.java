package com.itla.postapp.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.itla.postapp.model.LoginCredentials;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private final LoginCredentials credentials;

    public LoginViewModelFactory(LoginCredentials credentials) {
        this.credentials = credentials;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T) new LoginViewModel(credentials);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
