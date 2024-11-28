package com.example.electric;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CalculateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        // Initialize BottomNavigationView for AboutActivity
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set the selected item to "Calculate" when this activity is created
        bottomNavigationView.setSelectedItemId(R.id.calculate);

        // Set up listener for navigation item selection
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                // Navigate back to MainActivity when "Home" is selected
                Intent intent = new Intent(CalculateActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.about) {
                // Navigate to AboutActivity when "About" is selected
                Intent intent = new Intent(CalculateActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.calculate) {
                return true;
            }

            return false;
        });

        // Initialize the views for user input and result display
        EditText unitInput = findViewById(R.id.unitInput);
        EditText rebateInput = findViewById(R.id.rebateInput);
        Button calculateButton = findViewById(R.id.button3);
        TextView outputText = findViewById(R.id.outputText);
        TextView errorText = findViewById(R.id.errorText); // Reference to error message

        // Set a click listener on the Calculate button
        calculateButton.setOnClickListener(v -> {
            // Clear any previous error messages
            errorText.setText("");

            // Get user inputs
            String unitStr = unitInput.getText().toString();
            String rebateStr = rebateInput.getText().toString();

            if (unitStr.isEmpty() || rebateStr.isEmpty()) {
                // Display error message if input is missing
                errorText.setText("Please fill in all fields.");
            } else {
                try {
                    // Parse inputs to double
                    double units = Double.parseDouble(unitStr);
                    double rebate = Double.parseDouble(rebateStr);

                    // Validate rebate value
                    if (rebate < 0 || rebate > 5) {
                        errorText.setText("Rebate must be between 0% and 5%.");
                        return;
                    }

                    // Calculate the electricity bill
                    double bill = 0;
                    if (units <= 200) {
                        bill = units * 0.218;
                    } else if (units <= 300) {
                        bill = (200 * 0.218) + ((units - 200) * 0.334);
                    } else if (units <= 600) {
                        bill = (200 * 0.218) + (100 * 0.334) + ((units - 300) * 0.516);
                    } else {
                        bill = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((units - 600) * 0.546);
                    }

                    // Apply the rebate to the bill
                    bill -= (bill * (rebate / 100));

                    // Display the result in the output TextView
                    outputText.setText("Total Bill: $" + String.format("%.2f", bill));

                } catch (NumberFormatException e) {
                    // Handle case where input is not a valid number
                    errorText.setText("Please enter valid numeric values.");
                }
            }
        });
    }
}
