package com.itla.postapp.webservice.service;

import com.itla.postapp.model.post.Post;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {
    @GET("post")
    Call<List<Post>> findAll();
}
