package com.itla.postapp.webservice;

import com.itla.postapp.webservice.service.LoginService;

public class LoginClient implements Client{ ;

    private LoginService service;

    public LoginClient(){
        service = ServiceGenerator.getInstance()
                .createService(LoginService.class);
    }

    public <S> S getService(){
        return (S) service;
    }
}
