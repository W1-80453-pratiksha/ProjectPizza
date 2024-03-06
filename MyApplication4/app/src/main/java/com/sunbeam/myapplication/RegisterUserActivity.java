package com.sunbeam.myapplication;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.sunbeam.myapplication.R;
import com.sunbeam.myapplication.RetrofitClient;
import com.sunbeam.myapplication.LoginActivity;
import com.sunbeam.myapplication.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUserActivity extends AppCompatActivity {
    EditText editFirstName ,editLastName,editEmail,editPassword,editConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        editFirstName =findViewById(R.id.editFirstName);
        editLastName =findViewById(R.id.editLastName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPassword =findViewById(R.id.editConfirmPassword);
    }


    public void  onCancle(View view) {
        Intent intent =new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();    }

    public void onRegister(View view) {
        User user = new User();
        user.setFirstName(editFirstName.getText().toString());
        user.setLastName(editLastName.getText().toString());
        user.setEmail(editEmail.getText().toString());
        user.setPassword(editPassword.getText().toString());
        if(user.getEmail().equals("") || user.getPassword().equals(""))
            Toast.makeText(this, "email or password cannot be empty", Toast.LENGTH_SHORT).show();
        else
        if(!editConfirmPassword.getText().toString().equals(editPassword.getText().toString())) {
            Toast.makeText(this, "Password does no match", Toast.LENGTH_SHORT).show();

        }
            RetrofitClient.getInstance().getApi().registerUser(user).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                    assert response.body() != null;
                    if(response.body().get("status").getAsString().equals("success")){
                        finish();
                    }
                    else if (response.body().get("error").getAsJsonObject().get("errno").toString().equals(1062)){
                            Toast.makeText(RegisterUserActivity.this, "email already exists", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegisterUserActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(RegisterUserActivity.this, "Something went wrong while registration", Toast.LENGTH_SHORT).show();
                }
            });
        }

}