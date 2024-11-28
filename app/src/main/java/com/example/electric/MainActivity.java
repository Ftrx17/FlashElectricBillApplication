package com.example.electric;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.electric.R;



import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set up listener for navigation item selection
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                // Stay on the home page
                return true;
            } else if (itemId == R.id.about) {
                // Navigate to AboutActivity when "About" is selected
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.calculate) {
                // Prevent re-opening the CalculateActivity if already on this page
                // Navigate to CalculateActivity when "Calculate" is selected
                Intent intent = new Intent(MainActivity.this, CalculateActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }
}




