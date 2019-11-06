package com.itla.postapp.webservice.service;

import com.itla.postapp.model.user.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersService {
    @GET("users")
    Call<List<User>> findAll();
}
