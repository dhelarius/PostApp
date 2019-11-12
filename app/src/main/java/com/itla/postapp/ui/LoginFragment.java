package com.itla.postapp.ui;


import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

    private static final String TAG = LoginFragment.class.getSimpleName();

    private View view;
    private FragmentLoginBinding binding;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false);

        EditText editEmail = binding.editEmail;
        EditText editPassword = binding.editPassword;

        view = binding.getRoot();

        binding.setLifecycleOwner(this);

        LoginViewModelFactory viewModelFactory = new LoginViewModelFactory(getActivity());
        LoginViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);

        binding.setLoginViewModel(viewModel);

        viewModel.isLogginIn().observe(this, isLogginIn -> {
                if (isLogginIn) {
                    showProgressBar();

                    String email = editEmail.getText().toString();
                    String password = editPassword.getText().toString();

                    LoginCredentials credentials = new LoginCredentials(email, password);
                    viewModel.setLoginCredentials(credentials);
                } else {
                    showLoginButton();
                }
        });

        viewModel.isLogged().observe(this, isLogged -> {
            if (isLogged) {
                navigateToPostsFragment();
                viewModel.hasBeenLogginIn();
                viewModel.hasBeenLogged();
            }
        });

        viewModel.inError().observe(this, isError -> {
            if(isError){
                Toast.makeText(getContext(), "Solicitud de inicio de sesión fallida!", Toast.LENGTH_SHORT).show();
                viewModel.anErrorHasOcurred();
                viewModel.hasBeenLogginIn();
            }
        });

        viewModel.getLoginStatus().observe(this, loginIsIncorrect -> {
            if(loginIsIncorrect){
                Toast.makeText(getContext(), "Inicio de sesión incorrecto!", Toast.LENGTH_SHORT).show();
                viewModel.thereWasAIncorrectLogin();
                viewModel.hasBeenLogginIn();
            }
        });

        return view;
    }

    private void navigateToPostsFragment() {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_postsFragment);
    }

    private void showProgressBar() {
        binding.setLoginButtonVisible(View.INVISIBLE);
        binding.setProgressBarVisible(View.VISIBLE);
    }

    private void showLoginButton() {
        binding.setLoginButtonVisible(View.VISIBLE);
        binding.setProgressBarVisible(View.INVISIBLE);
    }
}
