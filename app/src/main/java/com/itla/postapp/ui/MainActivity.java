package com.itla.postapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.itla.postapp.R;
import com.itla.postapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        NavController navController = Navigation.findNavController(this, R.id.navHostragment);
        BottomNavigationView bottomNav = binding.buttomNav;

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {

            int id = destination.getId();

            if(id == R.id.splashScreenFragment){
                toolbar.setVisibility(View.GONE);
                bottomNav.setVisibility(View.GONE);
            }

            if(id == R.id.loginFragment){
                toolbar.setVisibility(View.GONE);
                bottomNav.setVisibility(View.GONE);
            }

            if(id == R.id.postsFragment){
                toolbar.setVisibility(View.VISIBLE);
                bottomNav.setVisibility(View.VISIBLE);
            }

            if(id == R.id.usersFragment){
                toolbar.setVisibility(View.VISIBLE);
                bottomNav.setVisibility(View.VISIBLE);
            }

            if(id == R.id.profileFragment){
                toolbar.setVisibility(View.VISIBLE);
                bottomNav.setVisibility(View.VISIBLE);
            }

        });

        NavigationUI.setupWithNavController(bottomNav, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.navHostragment);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }
}
