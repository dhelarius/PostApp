package com.itla.postapp.ui;


import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.itla.postapp.R;
import com.itla.postapp.databinding.FragmentLoginBinding;
import com.itla.postapp.model.LoginCredentials;
import com.itla.postapp.ui.viewmodel.LoginViewModel;
import com.itla.postapp.ui.viewmodel.LoginViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

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

        LoginCredentials credentials =
                new LoginCredentials("djimenez@itla.com","aj1471992");

        LoginViewModelFactory viewModelFactory = new LoginViewModelFactory(getActivity(), credentials);
        LoginViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);

        binding.setLoginViewModel(viewModel);

        viewModel.isLoggedIn().observe(this, isLogged -> {
            if(isLogged){
                updateUI();
                viewModel.hasBeenLogged();
            }
        });

        return binding.getRoot();
    }

    private void updateUI() {
        Toast.makeText(getContext(), "Se ha logueado", Toast.LENGTH_SHORT).show();
    }

}
