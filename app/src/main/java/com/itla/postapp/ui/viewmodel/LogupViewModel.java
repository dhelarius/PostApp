package com.itla.postapp.ui.viewmodel;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.itla.postapp.model.user.User;
import com.itla.postapp.webservice.Client;
import com.itla.postapp.webservice.ClientFactory;
import com.itla.postapp.webservice.WebClient;
import com.itla.postapp.webservice.service.LogupService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogupViewModel extends ViewModel {

    private static String TAG = LogupViewModel.class.getSimpleName();

    private Client logupClient;
    private User user;

    private MutableLiveData<Boolean> registering = new MutableLiveData<>();
    private MutableLiveData<Boolean> registered = new MutableLiveData<>();
    private MutableLiveData<Boolean> incorrectLogup = new MutableLiveData<>();
    private MutableLiveData<Boolean> error = new MutableLiveData<>();

    LogupViewModel(String token){
        logupClient = ClientFactory.getWebClientService(WebClient.LOGUP_CLIENT, token);
    }

    public void setUser(User user){
        this.user = user;
    }

    public void logup(){

        registering.setValue(true);

        LogupService service = logupClient.getService();

        Call<User> call = service.logup(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Log.i("LogupViewModel", "Nombre: " + response.body().getName());
                    registered.setValue(true);
                }else{
                    incorrectLogup.setValue(true);
                    Log.i(TAG, "Inicio de sesión incorrecto :(");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                error.setValue(true);
                Log.i(TAG, "Registro fallido :(");
            }
        });
    }

    public LiveData<Boolean> isRegistering(){
        return registering;
    }

    public void hasBeenRegistering(){
        registering.setValue(false);
    }

    public LiveData<Boolean> isRegistered(){
        return registered;
    }

    public void hasBeenRegistered(){
        registered.setValue(false);
    }

    public LiveData<Boolean> isLogupIncorrect(){
        return incorrectLogup;
    }

    public void thereWasAIncorrectLogup(){ incorrectLogup.setValue(false); }

    public LiveData<Boolean> inError(){ return error; }

    public void anErrorHasOcurred(){ error.setValue(false); }
}
