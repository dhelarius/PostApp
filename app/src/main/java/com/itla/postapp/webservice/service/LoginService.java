package com.itla.postapp.webservice.service;

import com.itla.postapp.model.LoginCredentials;
import com.itla.postapp.model.user.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("login")
    Call<LoginResponse> login(@Body LoginCredentials credentials);
}
