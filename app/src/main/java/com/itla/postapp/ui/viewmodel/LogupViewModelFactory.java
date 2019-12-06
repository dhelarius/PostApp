package com.itla.postapp.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LogupViewModelFactory implements ViewModelProvider.Factory {

    private final String token;

    public LogupViewModelFactory(String token){
       this.token = token;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(LogupViewModel.class)){
            return (T) new LogupViewModel(token);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
