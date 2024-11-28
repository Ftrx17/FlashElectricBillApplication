package com.example.electric;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Initialize BottomNavigationView for AboutActivity
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set the selected item to "About" when this activity is created
        bottomNavigationView.setSelectedItemId(R.id.about);

        // Set up listener for navigation item selection in AboutActivity
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                // Navigate back to MainActivity when "Home" is selected
                Intent intent = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.about) {
                // Stay on AboutActivity when About is selected
                return true;
            } else if (itemId == R.id.calculate) {
                // Navigate to CalculateActivity when "Calculate" is selected
                Intent intent = new Intent(AboutActivity.this, CalculateActivity.class);
                startActivity(intent);
            }

            return false;
        });

        // Set up the clickable link functionality
        TextView textViewLink = findViewById(R.id.textViewLink);

        // Set an OnClickListener to the TextView
        textViewLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a URL when the TextView is clicked
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.example.com"));
                startActivity(browserIntent);
            }
        });
    }
}

