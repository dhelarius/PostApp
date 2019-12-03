package com.itla.postapp.ui;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itla.postapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class usersFragment extends Fragment {

    public usersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.users));

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

}
