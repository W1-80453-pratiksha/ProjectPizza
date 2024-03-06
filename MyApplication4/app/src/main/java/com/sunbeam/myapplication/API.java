package com.sunbeam.myapplication;


import com.google.gson.JsonObject;
import com.sunbeam.myapplication.User;

import java.lang.ref.Reference;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {
    public static final String BASE_URL ="http://192.168.1.114:4000";


    @POST("/user/signup")
    public Call<JsonObject> registerUser(@Body User user);

    @POST("/user/signin")
    public Call<JsonObject> loginUser(@Body User user);

    @GET("/user/{id}")
    public Call<JsonObject> getUser(@Path("id") int id);

    @GET("/user/")
    public Call<JsonObject> getAllUsers();

    @GET("/veg_pizza")
    public Call<JsonObject> get_Pizza();


    @GET("/veg_pizza/veg")
    public Call<JsonObject> veg_Pizza();

    @GET("/veg_pizza/non_veg")
    public Call<JsonObject> getV_Name();
    @GET("/order/{userId}")
    public Call<JsonObject> get_order(@Path("userId") int userId);

    @POST("/cart/cart/{userId}")
    public Call<JsonObject> addToCart(@Body CartItem cartItem,@Path("userId") int userId);
    @GET("/cart/cart")
    Call<List<CartItem>> getCart();

    @GET("cart/details/{userId}")
    Call<JsonObject> getCartItems(@Path("userId") int userId);

   @DELETE("/cart/cart/{p_id}")
    Call<JsonObject> removePizza(@Path(value = "p_id" , encoded = true) int p_id);
   @POST("/order/{userId}")
    Call<JsonObject> addToOrder(@Body CartItem cartItem, @Path("userId")int userId);
}
