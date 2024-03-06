package com.sunbeam.myapplication;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sunbeam.myapplication.RetrofitClient;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LoginActivity extends AppCompatActivity {

    EditText EditMail, EditPass;
    TextView TextRegister;
    CheckBox checkBox;

    Button BtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditMail = findViewById(R.id.editMail);
        EditPass = findViewById(R.id.editPass);
        BtnLogin = findViewById(R.id.btnLogin);
        checkBox=findViewById(R.id.checkRememberMe);
        TextRegister=findViewById(R.id.textRegister);
    }
    public void Login(View view) {
        User user = new User();
        user.setEmail(EditMail.getText().toString());
        user.setPassword(EditPass.getText().toString());

        RetrofitClient.getInstance().getApi().loginUser(user).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Log.e("Helo", response.body().toString());
                    String responseJson = String.valueOf(response.body());
                    JsonObject jsonResponse = new Gson().fromJson(responseJson, JsonObject.class);

// Check if the "status" field is "success"
                    if (jsonResponse.has("status") && jsonResponse.get("status").getAsString().equals("success")) {
                        // Get the "data" object
                        JsonObject dataObject = jsonResponse.getAsJsonObject("data");

                        // Check if the "data" object is not null
                        if (dataObject != null) {
                            // Get the "userId" field
                            int userId = dataObject.get("userId").getAsInt();
                            String firstName = dataObject.get("firstName").getAsString();
                            String lastName = dataObject.get("lastName").getAsString();
                            String email=dataObject.get("email").getAsString();


                            // Now you can use the userId as needed
                            // For example, store it, use it in your application logic, etc.
                            System.out.println("User ID: " + userId);


                            UserProfileManager.saveUser(LoginActivity.this,userId,firstName,lastName,email);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Email or password is wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Failed to login, please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }


        });
    }









//    public void Login(View view) {
//        String email = EditMail.getText().toString();
//        String password = EditPass.getText().toString();
//
//        // Check if email is empty
//        if (email.isEmpty()) {
//            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_LONG).show();
//            return; // Exit the method early if email is empty
//        }
//
//        // Check if password is empty
//        if (password.isEmpty()) {
//            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show();
//            return; // Exit the method early if password is empty
//        }
//
//        // If email and password are not empty, proceed with login
//        Log.e("login", email);
//        Log.e("login", password);
//        public void Login(View view) {
//            User user = new User();
//            user.setEmail(EditMail.getText().toString());
//            user.setPassword(EditPass.getText().toString());
//
//            RetrofitClient.getInstance().getApi().loginUser(user).enqueue(new Callback<JsonObject>() {
//                @Override
//                public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
//                    if (response.isSuccessful()) {
//                        JsonObject jsonObject = response.body();
//                        if (jsonObject != null && jsonObject.has("status") && jsonObject.get("status").getAsString().equals("success")) {
//                            JsonArray array = jsonObject.get("data").getAsJsonArray();
//                            if (array.size() != 0) {
//                                int id = array.get(0).getAsJsonObject().get("id").getAsInt();
//                                getSharedPreferences(PizzaConstants.SHARED_PREFERENCE_FILE_NAME, MODE_PRIVATE)
//                                        .edit()
//                                        .putInt(PizzaConstants.USER_ID, id)
//                                        .apply();
//                                if (Objects.equals(user.getEmail(), array.get(0).getAsJsonObject().get("email").getAsString()) && Objects.equals(user.getPassword(), array.get(0).getAsJsonObject().get("password").getAsString())) {
//                                    if (checkBox.isChecked()) {
//                                        getSharedPreferences(PizzaConstants.SHARED_PREFERENCE_FILE_NAME, MODE_PRIVATE)
//                                                .edit()
//                                                .putBoolean(PizzaConstants.LOGIN_STATUS, true)
//                                                .apply();
//                                    }
//                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                    finish();
//                                }
//                            } else {
//                                Toast.makeText(LoginActivity.this, "Email or password is wrong", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    } else {
//                        Toast.makeText(LoginActivity.this, "Failed to login, please try again", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
//                    Log.e("Message", t.getLocalizedMessage());
//                    Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
////            startActivity(new Intent(LoginActivity.this, MainActivity.class));
////                           finish();
//
//        }





    public void gotoRegister(View view){
        Intent intent=new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }
}
