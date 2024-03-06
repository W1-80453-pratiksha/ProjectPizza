package com.sunbeam.myapplication;// CartResponse.java
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CartResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<CartItem> data;

    public String getStatus() {
        return status;
    }

    public List<CartItem> getData() {
        return data;
    }
}
