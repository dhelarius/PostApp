package com.itla.postapp.webservice.service;

import com.itla.postapp.model.user.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LogupService {
    @POST("register")
    Call<User> logup(@Body User user);
}
