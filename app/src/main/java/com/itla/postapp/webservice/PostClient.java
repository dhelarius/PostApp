package com.itla.postapp.webservice;

import com.itla.postapp.webservice.service.PostService;

public class PostClient implements Client {

    private PostService service;

    public PostClient(String token){
        service = ServiceGenerator.getInstance()
                .createService(PostService.class, token);
    }

    @Override
    public <S> S getService() {
        return (S) service;
    }
}
