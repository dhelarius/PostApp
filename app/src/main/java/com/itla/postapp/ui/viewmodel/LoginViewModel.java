package com.itla.postapp.ui.viewmodel;

import android.app.Activity;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.itla.postapp.model.LoginCredentials;
import com.itla.postapp.model.user.LoginResponse;
import com.itla.postapp.preference.Preference;
import com.itla.postapp.preference.TokenPreference;
import com.itla.postapp.webservice.Client;
import com.itla.postapp.webservice.ClientFactory;
import com.itla.postapp.webservice.WebClient;
import com.itla.postapp.webservice.service.LoginService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private static final String TAG = LoginViewModel.class.getSimpleName();

    private Client loginClient;
    private LoginCredentials credentials;
    private Preference<String> tokenPreference;

    private MutableLiveData<Boolean> logginIn = new MutableLiveData<>();
    private MutableLiveData<Boolean> logged = new MutableLiveData<>();
    private MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();
    private MutableLiveData<Boolean> error = new MutableLiveData<>();

    LoginViewModel(Activity activity, LoginCredentials credentials){
        this.credentials = credentials;
        tokenPreference = new TokenPreference(activity);
        loginClient = ClientFactory.getWebClientService(WebClient.LOGIN_CLIENT);
    }

    public void login(){

        logginIn.setValue(true);

        LoginService service = loginClient.getService();

        Call<LoginResponse> call = service.login(credentials);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    String token = response.body().getToken();
                    tokenPreference.write(token);
                    logged.setValue(true);
                    Log.i(TAG, "token: " + token);
                }else{
                    loginStatus.setValue(true);
                    Log.i(TAG, "Inicio de sesión incorrecto :(");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                error.setValue(true);
                Log.i(TAG, "Solicitud de inicio de sesión fallida :(");
            }
        });
    }

    public LiveData<Boolean> isLogginIn(){ return logginIn; }

    public void hasBeenLogginIn(){ logginIn.setValue(false); }


    public LiveData<Boolean> isLogged(){ return logged; }

    public void hasBeenLogged(){ logged.setValue(false); }


    public LiveData<Boolean> inError(){ return error; }

    public void anErrorHasOcurred(){ error.setValue(false); }


    public LiveData<Boolean> getLoginStatus(){ return loginStatus; }

    public void thereWasAIncorrectLogin(){ loginStatus.setValue(false); }
}
