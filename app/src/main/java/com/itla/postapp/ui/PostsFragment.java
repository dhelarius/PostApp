package com.itla.postapp.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.itla.postapp.R;
import com.itla.postapp.databinding.FragmentPostsBinding;
import com.itla.postapp.preference.TokenPreference;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment {

    private static String TAG = PostsFragment.class.getSimpleName();

    private View view;

    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentPostsBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_posts, container, false);

        view = binding.getRoot();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.posts));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String token = TokenPreference.getInstance(getActivity()).read();

        if(token.isEmpty()){
            navigateToLoginFragment();
            Log.i(TAG, "Token is empty");
        }else{
            Log.i(TAG, "Token is not empty");
        }
    }

    private void navigateToLoginFragment() {
        Navigation.findNavController(view).navigate(R.id.action_postsFragment_to_loginFragment);
    }
}
