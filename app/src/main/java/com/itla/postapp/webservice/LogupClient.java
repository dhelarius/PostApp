package com.itla.postapp.webservice;

import com.itla.postapp.webservice.service.LogupService;

public class LogupClient implements Client {

    private LogupService service;

    public LogupClient(String token){
        service = ServiceGenerator.getInstance()
                .createService(LogupService.class, token);
    }

    @Override
    public <S> S getService() {
        return (S) service;
    }
}
