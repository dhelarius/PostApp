package com.itla.postapp.webservice;

import android.app.Activity;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.itla.postapp.model.LoginCredentials;
import com.itla.postapp.model.user.LoginResponse;
import com.itla.postapp.preference.Preference;
import com.itla.postapp.preference.TokenPreference;
import com.itla.postapp.webservice.service.LoginService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginClient {

    private static final String TAG = LoginClient.class.getSimpleName();

    private LoginService service;
    private LoginCredentials credentials;
    private Activity activity;

    public LoginClient(Activity activity, LoginCredentials credentials){
        this.activity = activity;
        this.credentials = credentials;
        service = ServiceGenerator.getInstance()
                .createService(LoginService.class);
    }

    public LiveData<Boolean> login(){
        Preference<String> tokenPreference = new TokenPreference(activity);
        MutableLiveData<Boolean> data = new MutableLiveData<>();
        Call<LoginResponse> call = service.login(credentials);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    String token = response.body().getToken();
                    tokenPreference.write(token);
                    data.setValue(true);
                }else{
                    Log.i(TAG, "Inicio de sesión incorrecto :(");
                    data.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.i(TAG, "Solicitud de inicio de sesión fallida :(");
                    data.setValue(false);
            }
        });

        return data;
    }
}
