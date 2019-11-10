package com.itla.postapp.ui;


import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.itla.postapp.R;
import com.itla.postapp.databinding.FragmentLoginBinding;
import com.itla.postapp.model.LoginCredentials;
import com.itla.postapp.ui.viewmodel.LoginViewModel;
import com.itla.postapp.ui.viewmodel.LoginViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private View view;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentLoginBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false);

        view = binding.getRoot();

        binding.setLifecycleOwner(this);

        LoginCredentials credentials =
                new LoginCredentials("djimenez@itla.com","aj1471992");

        LoginViewModelFactory viewModelFactory = new LoginViewModelFactory(getActivity(), credentials);
        LoginViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);

        binding.setLoginViewModel(viewModel);

        viewModel.isLoggedIn().observe(this, isLogged -> {
            if(isLogged){
                navigateToPostsFragment();
                viewModel.hasBeenLogged();
            }
        });

        return view;
    }

    private void navigateToPostsFragment() {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_postsFragment);
    }

}
