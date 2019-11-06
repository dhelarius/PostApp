package com.itla.postapp.webservice;

import com.itla.postapp.model.LoginCredentials;
import com.itla.postapp.model.user.LoginResponse;
import com.itla.postapp.webservice.service.LoginService;
import retrofit2.Call;

public class LoginClient {

    private LoginService service;
    private LoginCredentials credentials;

    public LoginClient(LoginCredentials credentials){
        this.credentials = credentials;
        service = ServiceGenerator.getInstance()
                .createService(LoginService.class);
    }

    public LoginService getService(){
        return service;
    }

    public Call<LoginResponse> getCall(){
        return service.login(credentials);
    }
}
