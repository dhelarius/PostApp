package com.itla.postapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.itla.postapp.R;
import com.itla.postapp.model.LoginCredentials;
import com.itla.postapp.preference.Preference;
import com.itla.postapp.preference.TokenPreference;
import com.itla.postapp.ui.viewmodel.LoginViewModel;
import com.itla.postapp.ui.viewmodel.LoginViewModelFactory;
import com.itla.postapp.webservice.LoginClient;

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

        LoginViewModelFactory viewModelFactory = new LoginViewModelFactory(this, credentials);
        LoginViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);

        loginClient = new LoginClient(this, credentials);

        loginButton.setOnClickListener(v -> {
            viewModel.login().observe(this, isLogin -> {
                if(isLogin){
                    Preference<String> tokenPreference = new TokenPreference(this);
                    String token = tokenPreference.read();
                    tokenText.setText(token);
                }
            });
        });
    }
}
