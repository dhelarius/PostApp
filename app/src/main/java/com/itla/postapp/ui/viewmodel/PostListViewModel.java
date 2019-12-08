package com.itla.postapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.itla.postapp.model.post.Post;
import com.itla.postapp.webservice.Client;
import com.itla.postapp.webservice.ClientFactory;
import com.itla.postapp.webservice.WebClient;
import com.itla.postapp.webservice.service.PostService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostListViewModel extends ViewModel {

    private static final String TAG = PostListViewModel.class.getSimpleName();

    private MutableLiveData<List<Post>> posts = new MutableLiveData<>();

    private Client postClient;

    PostListViewModel(String token){
        postClient = ClientFactory.getWebClientService(WebClient.POST_CLIENT, token);
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
}
