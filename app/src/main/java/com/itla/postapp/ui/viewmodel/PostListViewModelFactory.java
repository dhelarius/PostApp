package com.itla.postapp.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PostListViewModelFactory implements ViewModelProvider.Factory {

    private final String token;

    public PostListViewModelFactory(String token) {
        this.token = token;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PostListViewModel.class)) {
            return (T) new PostListViewModel(token);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
