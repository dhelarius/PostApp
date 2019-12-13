package com.itla.postapp.webservice.service;

import com.itla.postapp.model.post.Post;
import com.itla.postapp.model.post.PostResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostService {
    @GET("post")
    Call<List<Post>> findAll();

    @POST("post")
    Call<PostResponse> publish(@Body Post post);
}
