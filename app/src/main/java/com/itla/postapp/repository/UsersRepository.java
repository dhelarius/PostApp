package com.itla.postapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.itla.postapp.model.user.User;
import com.itla.postapp.webservice.ServiceGenerator;
import com.itla.postapp.webservice.service.UsersService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {

    private UsersService service;

    public UsersRepository(){
        service = ServiceGenerator.getInstance().createService(UsersService.class, "");
    }

    public LiveData<List<User>> findAll(){
        MutableLiveData<List<User>> data = new MutableLiveData<>();
        Call<List<User>> call = service.findAll();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

        return data;
    }
}
