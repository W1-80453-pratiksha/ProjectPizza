package com.sunbeam.myapplication;

import java.io.Serializable;

// CartItem.java
public class CartItem implements Serializable {

    private int id; // Replace with your actual identifier for the item
    private int userId;
    private int p_Id;
    private String p_name;
    private int quantity;
    private String details;
    private double price;
    private double amount;
    private String image;
    // Total price for the quantity

    // Constructor
    public CartItem(int id,int userId, String p_name, int quantity, double price) {
        this.id = id;
        this.userId=userId;
        this.p_name = p_name;
        this.quantity = quantity;
        this.price = price;
        this.amount=quantity*price;

    }

    public CartItem(String p_name,String details,double price,int p_Id,String image,int quantity,double amount) {

        this.p_Id = p_Id;
        this.p_name = p_name;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
        this.image = image;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return p_name;
    }

    public void setName(String name) {
        this.p_name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + p_name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    public int getP_Id() {
        return  this.p_Id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setP_Id(int p_Id) {
        this.p_Id = p_Id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

