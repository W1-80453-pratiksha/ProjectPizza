package com.sunbeam.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPizzas;
    private PizzaAdapter pizzaAdapter;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerViewPizzas = findViewById(R.id.recyclerViewPizzas);
        bottomNavigationView = findViewById(R.id.bottom_navigation); // Updated ID

        // Set up RecyclerView and adapter
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewPizzas.setLayoutManager(layoutManager);

        // Dummy list of pizzas for testing, replace it with your API call logic
        List<Pizza> pizzaList = getDummyPizzaList();
        pizzaAdapter = new PizzaAdapter(pizzaList,this);
        recyclerViewPizzas.setAdapter(pizzaAdapter);

        // Set the listener for Bottom Navigation View
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

    // Dummy method to create a list of pizzas for testing
    private List<Pizza> getDummyPizzaList() {
        List<Pizza> pizzas=new ArrayList<>();

        // Call the getPizzas method


        // Enqueue the call to handle the response asynchronously
        RetrofitClient.getInstance().getApi().get_Pizza().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(retrofit2.Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.e("Helooooo",response.body().toString());
                    JsonArray pizza= response.body().get("data").getAsJsonArray();
                    Log.e("Helllllloooooo",pizza.toString());

                    for(JsonElement element : pizza)
                    {
                        JsonObject object=element.getAsJsonObject();
                        int Id=object.get("Id").getAsInt();
                        String p_name=object.get("p_name").getAsString();
                        String details=object.get("details").getAsString();
                        String image=object.get("image").getAsString();
                        float price=object.get("price").getAsFloat();
                        String category=object.get("category").getAsString();

                        Pizza pizza1=new Pizza(Id,p_name,details,price,category,image);
                        pizzas.add(pizza1);


                    }

                    // Process the list of pizzas as needed
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(MainActivity.this, "Failed to fetch pizzas", Toast.LENGTH_SHORT).show();
                };
            }

            @Override
            public void onFailure(retrofit2.Call<JsonObject> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }


        });
//        pizzas.add(new Pizza("Margherita", "url_to_margherita_image"));
//        pizzas.add(new Pizza("Pepperoni", "url_to_pepperoni_image"));
//        pizzas.add(new Pizza("Margherita", "url_to_margherita_image"));
//        pizzas.add(new Pizza("Pepperoni", "url_to_pepperoni_image"));
//        pizzas.add(new Pizza("Margherita", "url_to_margherita_image"));
//        pizzas.add(new Pizza("Pepperoni", "url_to_pepperoni_image"));
//        pizzas.add(new Pizza("Margherita", "url_to_margherita_image"));
//        pizzas.add(new Pizza("Pepperoni", "url_to_pepperoni_image"));
//        pizzas.add(new Pizza("Margherita", "url_to_margherita_image"));
//        pizzas.add(new Pizza("Pepperoni", "url_to_pepperoni_image"));
//        pizzas.add(new Pizza("Margherita", "url_to_margherita_image"));
//        pizzas.add(new Pizza("Pepperoni", "url_to_pepperoni_image"));

        return pizzas;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_cart) {
            Intent intent=new Intent(this,CartActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_filter) {
            // Handle Filter menu item click
            // This is the main "Filter" menu item, no action needed here
            return true;
        } else if (itemId == R.id.submenu_veg_pizzas) {
            List<Pizza> pizzas=new ArrayList<>();

            // Call the getPizzas method


            // Enqueue the call to handle the response asynchronously
            RetrofitClient.getInstance().getApi().veg_Pizza().enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(retrofit2.Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        Log.e("Helooooo",response.body().toString());
                        JsonArray pizza= response.body().get("data").getAsJsonArray();
                        Log.e("Helllllloooooo",pizza.toString());

                        for(JsonElement element : pizza)
                        {
                            JsonObject object=element.getAsJsonObject();
                            int Id=object.get("Id").getAsInt();
                            String p_name=object.get("p_name").getAsString();
                            String details=object.get("details").getAsString();
                            String image=object.get("image").getAsString();
                            float price=object.get("price").getAsFloat();
//                           String category=object.get("category").getAsString();

                            Pizza pizza1=new Pizza(Id,p_name,details,price,image);
                            pizzas.add(pizza1);


                        }


                        // Process the list of pizzas as needed
                    } else {
                        // Handle unsuccessful response
                        Toast.makeText(MainActivity.this, "Failed to fetch pizzas", Toast.LENGTH_SHORT).show();
                    };
                }

                @Override
                public void onFailure(retrofit2.Call<JsonObject> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                }



            });
            pizzaAdapter.setPizzaList(pizzas);
            pizzaAdapter.notifyDataSetChanged();

            return true;
        } else if (itemId == R.id.submenu_non_veg_pizzas) {
            List<Pizza> pizzas=new ArrayList<>();

            // Call the getPizzas method


            // Enqueue the call to handle the response asynchronously
            RetrofitClient.getInstance().getApi().getV_Name().enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(retrofit2.Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        Log.e("Helooooo",response.body().toString());
                        JsonArray pizza= response.body().get("data").getAsJsonArray();
                        Log.e("Helllllloooooo",pizza.toString());

                        for(JsonElement element : pizza)
                        {
                            JsonObject object=element.getAsJsonObject();
                            int Id=object.get("Id").getAsInt();
                            String p_name=object.get("p_name").getAsString();
                            String details=object.get("details").getAsString();
                            String image=object.get("image").getAsString();
                            float price=object.get("price").getAsFloat();
//                            String category=object.get("category").getAsString();

                            Pizza pizza1=new Pizza(Id,p_name,details,price,image);
                            pizzas.add(pizza1);



                        }

                        // Process the list of pizzas as needed
                    } else {
                        // Handle unsuccessful response
                        Toast.makeText(MainActivity.this, "Failed to fetch pizzas", Toast.LENGTH_SHORT).show();
                    };
                }

                @Override
                public void onFailure(retrofit2.Call<JsonObject> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                }



            });
            pizzaAdapter.setPizzaList(pizzas);
            pizzaAdapter.notifyDataSetChanged();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}





//package com.sunbeam.myapplication;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.recyclerview.widget.GridLayoutManager;
////import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerViewPizzas;
//    private PizzaAdapter pizzaAdapter;
//    BottomNavigationView bottomNavigationView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        recyclerViewPizzas = findViewById(R.id.recyclerViewPizzas);
//        bottomNavigationView = findViewById(R.id.bottom_navigation);
//
//        // Set up RecyclerView and adapter
//        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
//        recyclerViewPizzas.setLayoutManager(layoutManager);
//
//        // Dummy list of pizzas for testing, replace it with your API call logic
//        List<Pizza> pizzaList = getDummyPizzaList();
//        pizzaAdapter = new PizzaAdapter(pizzaList);
//        recyclerViewPizzas.setAdapter(pizzaAdapter);
//    }
//
//    // Dummy method to create a list of pizzas for testing
//    private List<Pizza> getDummyPizzaList() {
//        List<Pizza> pizzas = new ArrayList<>();
//        pizzas.add(new Pizza("Margherita", "url_to_margherita_image"));
//        pizzas.add(new Pizza("Pepperoni", "url_to_pepperoni_image"));
//        pizzas.add(new Pizza("Margherita", "url_to_margherita_image"));
//        pizzas.add(new Pizza("Pepperoni", "url_to_pepperoni_image"));
//        pizzas.add(new Pizza("Margherita", "url_to_margherita_image"));
//        pizzas.add(new Pizza("Pepperoni", "url_to_pepperoni_image"));
//        pizzas.add(new Pizza("Margherita", "url_to_margherita_image"));
//        pizzas.add(new Pizza("Pepperoni", "url_to_pepperoni_image"));
//        pizzas.add(new Pizza("Margherita", "url_to_margherita_image"));
//        pizzas.add(new Pizza("Pepperoni", "url_to_pepperoni_image"));
//        pizzas.add(new Pizza("Margherita", "url_to_margherita_image"));
//        pizzas.add(new Pizza("Pepperoni", "url_to_pepperoni_image"));
//        // Add more pizzas as needed
//        return pizzas;
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//
//
//            bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
//        int itemId = item.getItemId();
//
//        if (itemId == R.id.action_home) {
//            // Handle Home selection
//            // Example: openHomeFragment();
//            return true;
//        } else if (itemId == R.id.action_profile) {
//            // Handle My Profile selection
//            // Example: openProfileFragment();
//            return true;
//        } else if (itemId == R.id.action_orders) {
//            // Handle My Orders selection
//            // Example: openOrdersFragment();
//            return true;
//        }
//
//        return false;
//    });
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int itemId = item.getItemId();
//
//        if (itemId == R.id.action_settings) {
//            // Handle Settings menu item click
//            // Example: openSettingsActivity();
//            return true;
//        } else if (itemId == R.id.action_cart) {
//            // Handle Cart menu item click
//            // Example: openCartActivity();
//            return true;
//        } else {
//            return super.onOptionsItemSelected(item);
//        }
//    }
//}
