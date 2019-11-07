package com.itla.postapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.itla.postapp.R;
import com.itla.postapp.model.LoginCredentials;
import com.itla.postapp.model.user.LoginResponse;
import com.itla.postapp.preference.Preference;
import com.itla.postapp.preference.TokenPreference;
import com.itla.postapp.webservice.LoginClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView tokenText;

    private LoginClient loginClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tokenText = findViewById(R.id.tokenText);
        Button loginButton = findViewById(R.id.loginButton);

        LoginCredentials credentials =
                new LoginCredentials("djimenez@itla.com","aj1471992");

        loginClient = new LoginClient(credentials);

        loginButton.setOnClickListener(v -> {
            login();
        });
    }

    private void login(){

        Preference<String> preference = new TokenPreference(this);

        Call<LoginResponse> call = loginClient.getCall();

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    preference.write(response.body().getToken());
                    tokenText.setText(response.body().getToken());
                    Toast.makeText(getBaseContext(),
                            "token: " + preference.read(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Inicio de sesión incorrecto :(",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Solicitud de inicio de sesión fallida :(",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
