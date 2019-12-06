package com.itla.postapp.ui;


import android.app.AlertDialog;
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
import com.itla.postapp.databinding.FragmentLogupBinding;
import com.itla.postapp.model.user.User;
import com.itla.postapp.preference.TokenPreference;
import com.itla.postapp.ui.viewmodel.LogupViewModel;
import com.itla.postapp.ui.viewmodel.LogupViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogupFragment extends Fragment {

    private View view;
    private FragmentLogupBinding binding;


    public LogupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_logup, container, false);

        view = binding.getRoot();

        EditText editName = binding.editName;
        EditText editEmail = binding.editEmail;
        EditText editPassword = binding.editPassword;

        binding.setLifecycleOwner(this);

        String token = TokenPreference.getInstance(getActivity()).read();

        LogupViewModelFactory viewModelFactory = new LogupViewModelFactory(token);
        LogupViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(LogupViewModel.class);

        binding.setLogupViewModel(viewModel);

        viewModel.isRegistering().observe(this, isRegistering -> {
            if(isRegistering){
               showProgressBar();

               String name = editName.getText().toString();
               String email = editEmail.getText().toString();
               String password = editPassword.getText().toString();

                User user = new User(0,
                        email,
                        password, 0,
                        name, 0);

                viewModel.setUser(user);

            }else{
               showLogupButton();
            }
        });

        viewModel.isRegistered().observe(this, isRegistered -> {
            if(isRegistered){
                //Mostrar dialog de registro satisfactorio, cuando se presione ok navegar hasta LoginFragment
                showSuccessfulRegistrationDialog();
                viewModel.hasBeenRegistering();
                viewModel.hasBeenRegistered();
            }
        });

        viewModel.isLogupIncorrect().observe(this, isLogupIncorrect -> {
            if(isLogupIncorrect){
                Toast.makeText(getContext(), "No se ha podido proceder con el regitro!", Toast.LENGTH_SHORT).show();
                viewModel.thereWasAIncorrectLogup();
                viewModel.hasBeenRegistering();
            }
        });

        viewModel.inError().observe(this, isError -> {
            if(isError){
                Toast.makeText(getContext(), "Registro fallido!", Toast.LENGTH_SHORT).show();
                viewModel.anErrorHasOcurred();
                viewModel.hasBeenRegistering();
            }
        });

        return view;
    }

    private void showSuccessfulRegistrationDialog(){
        new AlertDialog.Builder(getContext())
                .setTitle("Registro satisfactorio")
                .setPositiveButton("OK", (dialog, which) -> {
                    navigateToLoginFragment();
                }).setCancelable(false)
                .show();
    }

    private void navigateToLoginFragment(){
        Navigation.findNavController(view).popBackStack();
    }

    private void showProgressBar() {
        binding.setLogupButtonVisible(View.INVISIBLE);
        binding.setProgressBarVisible(View.VISIBLE);
    }

    private void showLogupButton() {
        binding.setLogupButtonVisible(View.VISIBLE);
        binding.setProgressBarVisible(View.INVISIBLE);
    }

}
