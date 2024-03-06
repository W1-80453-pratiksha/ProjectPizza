package com.sunbeam.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    private static List<Pizza> pizzaList;
    private Context context;


    // Constructor to initialize the list
    public PizzaAdapter(List<Pizza> pizzaList, Context context) {
        this.pizzaList = pizzaList;
        this.context = context;

    }


    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
        notifyDataSetChanged(); // Notify the adapter that the data set has changed
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);

        // Bind pizza details to the ViewHolder
        holder.textPizzaName.setText(pizza.getP_name());
        holder.textPizzaDescription.setText(pizza.getDetails());
        holder.textPizzaPrice.setText(String.valueOf(pizza.getPrice()));
        Glide.with(context)
                .load(API.BASE_URL + "/" + pizza.getImage())
                .into(holder.imagePizza);
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    // ViewHolder class
    public class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView textPizzaName, textPizzaDescription, textPizzaPrice;
        Button addToCartButton;
        ImageView imagePizza;
        EditText editText;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            textPizzaName = itemView.findViewById(R.id.textPizzaName);
            textPizzaPrice = itemView.findViewById(R.id.textPizzaPrice);
            textPizzaDescription = itemView.findViewById(R.id.textPizzaDescription);
            imagePizza = itemView.findViewById(R.id.imagePizza);
            addToCartButton = itemView.findViewById(R.id.btnAddToCart);
            editText=itemView.findViewById(R.id.editTextQuantity);

            addToCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Perform actions when the "Add to Cart" button is clicked
                    Pizza pizza = pizzaList.get(getAdapterPosition());

                    // Create a CartItem object

                    String quantityInString=editText.getText().toString();
                    int quantity=Integer.parseInt(quantityInString);
                    int userId=UserProfileManager.getId(context);
                    CartItem cartItem = new CartItem(pizza.getId(),userId, pizza.getP_name(), quantity, pizza.getPrice());
                    Log.e("CArtttttttttttt",cartItem.toString());
                    Log.e("userID",String.valueOf(userId));

                    // Make the Retrofit call to add the pizza to the cart
                    RetrofitClient.getInstance().getApi().addToCart(cartItem,userId).enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                            if (response.isSuccessful()) {
                                Log.e("adddddddd",response.body().toString());
                                // Handle successful response (item added to cart)
                                Toast.makeText(addToCartButton.getContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.e("addddddddvvvvvvvvvvvv",response.body().toString());
                                // Handle unsuccessful response
                                Toast.makeText(addToCartButton.getContext(), "Failed to add item to cart", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            // Handle failure (network error, etc.)
                            Toast.makeText(addToCartButton.getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}
