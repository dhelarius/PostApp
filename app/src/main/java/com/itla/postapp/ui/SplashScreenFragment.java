package com.itla.postapp.ui;


import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.itla.postapp.R;
import com.itla.postapp.databinding.FragmentSplashScreenBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashScreenFragment extends Fragment {

    private static int MILLISECONDS = 2000;
    private View view;

    public SplashScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentSplashScreenBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_splash_screen, container, false);

        view = binding.getRoot();

        new Thread(() -> {
            try {
                Thread.sleep(MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            navigateToLoginFragment();
        }).start();

        return view;
    }

    private void navigateToLoginFragment() {
        Navigation.findNavController(view).navigate(R.id.action_splashScreenFragment_to_loginFragment);
    }

}
