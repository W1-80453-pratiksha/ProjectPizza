package com.sunbeam.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sunbeam.myapplication.OrderAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class OrdersActivity extends AppCompatActivity{

    private RecyclerView recyclerViewOrders;
    private OrderAdapter orderAdapter;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        ImageView backButton = findViewById(R.id.backButton);

        // Initialize RecyclerView
        recyclerViewOrders = findViewById(R.id.recyclerViewOrders);
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(this));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        List<Order> orderList = getDummyOrderList();
        Log.e("Optionnn",orderList.toString());

        // Initialize and set up the adapter
        orderAdapter = new OrderAdapter(orderList);
        recyclerViewOrders.setAdapter(orderAdapter);

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
        backButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              startActivity(new Intent(OrdersActivity.this,MainActivity.class));

                                          }
        });
    }


    // Dummy method to create a list of orders for testing
    private List<Order> getDummyOrderList() {
        List<Order> orders = new ArrayList<>();
//        orders.add(new Order(12,"Pratiksha",20, 1, 2,399));
//        orders.add(new Order(12,"Gauri",20, 1, 2,399));
        int id= getSharedPreferences(PizzaConstants.SHARED_PREFERENCE_FILE_NAME, MODE_PRIVATE)
                .getInt(PizzaConstants.USER_ID,-1);;
        RetrofitClient.getInstance().getApi().get_order(id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.e("Helooooo",response.body().toString());
                    JsonArray pizza= response.body().get("data").getAsJsonArray();
                    Log.e("Helllllloooooo",pizza.toString());

                    for(JsonElement element : pizza)
                    {
                        JsonObject object=element.getAsJsonObject();
                        int id=object.get("id").getAsInt();
                        String p_name=object.get("p_name").getAsString();
                        int quantity=object.get("quantity").getAsInt();
                        float amount=object.get("amount").getAsFloat();
                        String createdTimestamp=object.get("createdTimestamp").getAsString();
                        orders.add(new Order(id,p_name,0,0,quantity,amount,createdTimestamp));
                        Log.e("Good Morning",orders.toString());
                        orderAdapter = new OrderAdapter(orders);
                        recyclerViewOrders.setAdapter(orderAdapter);
                    }

                } else {
                    Toast.makeText(OrdersActivity.this, "Failed to fetch pizzas", Toast.LENGTH_SHORT).show();
                };
            }

            @Override
            public void onFailure(retrofit2.Call<JsonObject> call, Throwable t) {
                Toast.makeText(OrdersActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Log.e("Optionnnnnnnnnnnnnn",orders.toString());
        return orders;
    }


}
