package com.itla.postapp.ui;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.itla.postapp.R;
import com.itla.postapp.databinding.FragmentLoginBinding;
import com.itla.postapp.databinding.FragmentLoginBindingImpl;
import com.itla.postapp.model.LoginCredentials;
import com.itla.postapp.preference.Preference;
import com.itla.postapp.preference.TokenPreference;
import com.itla.postapp.ui.viewmodel.LoginViewModel;
import com.itla.postapp.ui.viewmodel.LoginViewModelFactory;
import com.itla.postapp.webservice.LoginClient;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private TextView tokenText;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentLoginBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false);

        binding.setLifecycleOwner(this);

        tokenText = binding.tokenText;
        Button loginButton = binding.loginButton;

        LoginCredentials credentials =
                new LoginCredentials("djimenez@itla.com","aj1471992");

        LoginViewModelFactory viewModelFactory = new LoginViewModelFactory(getActivity(), credentials);
        LoginViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);

        loginButton.setOnClickListener(v -> {
            viewModel.login().observe(this, login -> {
                if(login){
                    Preference<String> tokenPreference = new TokenPreference(getActivity());
                    String token = tokenPreference.read();
                    tokenText.setText(token);
                }
            });
        });

        return binding.getRoot();
    }

}
