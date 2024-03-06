package com.sunbeam.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    BottomNavigationView bottomNavigationView;
    List<CartItem> cartItems;
    API apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cartItems=new ArrayList<>();

        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set up RecyclerView and adapter
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerViewCart.setLayoutManager(layoutManager);

        // Fetch and display cart items
        cartItems=fetchAndDisplayCartItems();
        Log.e("tanujjjaaaaaaaaaaaa",cartItems.toString());
        displayCartItems(cartItems);
        cartAdapter=new CartAdapter(cartItems,this);
        recyclerViewCart.setAdapter(cartAdapter);
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

    private List<CartItem> fetchAndDisplayCartItems() {
        List<CartItem> cartItemList = new ArrayList<>();
        // Make a Retrofit call to get cart items for the logged-in user
        int userId = getUserId();
        RetrofitClient.getInstance().getApi().getCartItems(userId).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {


                if (response.isSuccessful() && response.body() != null) {
                    // Handle successful response
                    Log.e("hePratiksha",response.body().toString());
                    CartResponse cartResponse;
                    JsonElement pizza = response.body().get("data");
                    JsonArray data= response.body().get("data").getAsJsonArray();

                    Log.e("hePratiksha",data.toString());

                    Log.e("heGauri",cartItems.toString());
                    for (JsonElement element : data) {
                        // Now 'element' represents each item in the array
                        JsonObject itemObject = element.getAsJsonObject();

                        // Access individual fields in the itemObject
                        String p_name = itemObject.get("p_name").getAsString();
                        String details = itemObject.get("details").getAsString();
                        double price = itemObject.get("price").getAsDouble();
                        int p_Id = itemObject.get("Id").getAsInt();
                        String image=itemObject.get("image").getAsString();
                        int quantity=itemObject.get("quantity").getAsInt();
                        double amount=itemObject.get("amount").getAsDouble();

                        CartItem cartItem=new CartItem(p_name,details,price,p_Id,image,quantity,amount);
                        Log.e("Shivaniiii",cartItem.toString());
                        cartItemList.add(cartItem);
                        Log.e("Sujataaaaaaaaaaaa",cartItemList.toString());
                        cartAdapter=new CartAdapter(cartItemList,CartActivity.this);
                        recyclerViewCart.setAdapter(cartAdapter);
                    }
                } else {
                    Log.e("heGauri",response.body().toString());
                    // Handle the error
                    Log.e("API Call", "Failed to get cart items. Code: " + response.code());
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("hehjgjgjyf",t.getLocalizedMessage());
                Log.e("API Call", "Failed to get cart items. Error: " + t.getMessage());

            }


        });
        //Log.e("ghcfhfjgghhgh",cartItemList.get(0).toString());
        return cartItemList;

    }

    private void displayCartItems(List<CartItem> cartItems) {
        // For each cart item, fetch pizza details and display in RecyclerView

        }


    // Implement a method to get the logged-in user's ID
    private int getUserId() {
        int userId=UserProfileManager.getId(this);
        return userId; // Replace with actual user ID retrieval logic
    }
}
