package com.itla.postapp.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.itla.postapp.model.post.Post;
import com.itla.postapp.model.post.PostResponse;
import com.itla.postapp.webservice.Client;
import com.itla.postapp.webservice.ClientFactory;
import com.itla.postapp.webservice.WebClient;
import com.itla.postapp.webservice.service.PostService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {

    private static final String TAG = PostViewModel.class.getSimpleName();

    private MutableLiveData<List<Post>> posts = new MutableLiveData<>();

    private MutableLiveData<PostResponse> newPost = new MutableLiveData<>();

    private MutableLiveData<Boolean> sendingPost = new MutableLiveData<>();

    private Client postClient;

    private Post post;

    PostViewModel(String token){
        postClient = ClientFactory.getWebClientService(WebClient.POST_CLIENT, token);
    }

    public void setPost(Post post){
        this.post = post;
    }

    public LiveData<List<Post>> getPosts(){

       PostService service = postClient.getService();

       Call<List<Post>> call = service.findAll();

       call.enqueue(new Callback<List<Post>>() {
           @Override
           public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
               posts.setValue(response.body());
           }

           @Override
           public void onFailure(Call<List<Post>> call, Throwable t) {

           }
       });

       return posts;
    }

    public LiveData<PostResponse> publish(){

        sendingPost.setValue(true);

        PostService service = postClient.getService();

        Call<PostResponse> call = service.publish(post);

        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if(response.isSuccessful()) {
                    PostResponse post = response.body();
                    newPost.setValue(post);
                }else{
                    Log.i(TAG, "Error");
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.i(TAG, "Failure");
            }
        });

        return newPost;
    }

    public LiveData<Boolean> isPostSending(){
        return sendingPost;
    }

    public void hasBeenSendindPost(){
        sendingPost.setValue(false);
    }
}
