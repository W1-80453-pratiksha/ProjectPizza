package com.sunbeam.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private  List<CartItem> cartItemList;
    private Context context;

    public CartAdapter(List<CartItem> cartItemList, Context context) {
        this.cartItemList = cartItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);

        holder.textPizzaName.setText(cartItem.getName());
        holder.textQuantity.setText("Quantity: " + cartItem.getQuantity());
        holder.textAmount.setText("Amount: $" + cartItem.getAmount());

        // Load pizza image using Glide
        Glide.with(context)
                .load(API.BASE_URL + "/" + cartItem.getImage())
                .into(holder.imagePizza);
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView textPizzaName, textQuantity, textAmount;
        Button btnRemoveFromCart,btnPlaceOrder;
        ImageView imagePizza;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            textPizzaName = itemView.findViewById(R.id.textPizzaName);
            textQuantity = itemView.findViewById(R.id.textQuantity);
            textAmount = itemView.findViewById(R.id.textAmount);
            imagePizza = itemView.findViewById(R.id.imagePizza);
            btnRemoveFromCart = itemView.findViewById(R.id.btnRemoveFromCart);
            btnPlaceOrder=itemView.findViewById(R.id.btnPlaceOrder);

            btnRemoveFromCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItemFromCart();

                    // Handle remove from cart button click
                    // You can implement the logic to remove the item from the cart here
                }
            });

            btnPlaceOrder.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    CartItem cartItem = cartItemList.get(getAdapterPosition());
                    int userId=UserProfileManager.getId(context);
                    RetrofitClient.getInstance().getApi().addToOrder(cartItem,userId).enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if(response.isSuccessful())
                            {
                                Log.e("Gauriiiiiiiii",response.body().toString());
                                Toast.makeText(context,"Order Placed Succesfully",Toast.LENGTH_SHORT).show();
                                removeItemFromCart();
                            }
                            else {
                                Log.e("Pratikshaaaaaaaaaaaa",response.body().toString());
                                Toast.makeText(context,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Toast.makeText(context,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            });
        }
        public void removeItemFromCart()
        {
            CartItem cartItem = cartItemList.get(getAdapterPosition());
            int p_id=cartItem.getP_Id();
            RetrofitClient.getInstance().getApi().removePizza(p_id).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if(response.isSuccessful())
                    {
                        Toast.makeText(context,"Pizza remove Succesfully",Toast.LENGTH_SHORT).show();
                        cartItemList.remove(getAdapterPosition());
                        notifyItemChanged(getAdapterPosition());
                    }
                    else {
                        Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(context,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
