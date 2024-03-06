package com.sunbeam.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvUserId, tvEmail, tvFirstName, tvLastName, tvId;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Initialize views
        tvUserId = findViewById(R.id.tvUserId);
        tvEmail = findViewById(R.id.tvEmail);
        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);


        // Load user data and id from PizzaShopPreferencesManager
        String firstName = UserProfileManager.get_firstName(this);
        String lastName = UserProfileManager.get_lastName(this);
        String email = UserProfileManager.get_email(this);
        Log.e("hell",firstName);
        int id = UserProfileManager.getId(this);

        // Display user data and id
        tvUserId.setText("User ID: " + id);
        tvEmail.setText("User Email: " + email);
        tvFirstName.setText("user FirstName:  "+firstName);
        tvLastName.setText("user LastName:  "+lastName);

        // Extract data from the user string (you need to implement this based on your User class)
        // Example: User user = parseUserString(userString);
        // Then, set the values in the TextViews
        // tvFirstName.setText("First Name: " + user.getFirstName());
        // tvLastName.setText("Last Name: " + user.getLastName());
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.action_home) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (itemId == R.id.action_orders) {
                startActivity(new Intent(this, OrdersActivity.class));
                return true;
            } else if (itemId == R.id.action_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }

            return false;
        });
    }

    // Logout button click event
    public void logout(android.view.View view) {
        // Clear SharedPreferences using PizzaShopPreferencesManager
        UserProfileManager.clearData(this);

        // Terminate the app
        finishAffinity();
    }
}
