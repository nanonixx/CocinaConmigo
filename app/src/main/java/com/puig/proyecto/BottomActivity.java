package com.puig.proyecto;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.puig.proyecto.databinding.ActivityBottomBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class BottomActivity extends AppCompatActivity {

    ActivityBottomBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bottom);
          setContentView((binding = ActivityBottomBinding.inflate(getLayoutInflater())).getRoot());


        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController();
//        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
//        NavigationUI.setupWithNavController(binding.toolbar, navController);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}