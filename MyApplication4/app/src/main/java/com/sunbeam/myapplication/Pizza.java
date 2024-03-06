package com.sunbeam.myapplication;
import java.io.Serializable;

public class Pizza implements Serializable
{
    private int Id;
    private String p_name;

    private  String details;
    private String category;
    private String image;
    private double price;
    private String createdTimestamp;

    public Pizza() {
    }

    public Pizza(int id, String p_name, String details, double price, String category, String image) {
        Id = id;
        this.p_name = p_name;
        this.details = details;
        this.category = category;
        this.image = image;
        this.price = price;
    }

    public Pizza(int id, String p_name, String details, float price, String image) {
        Id = id;
        this.p_name=p_name;
        this.details = details;
        this.image = image;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "Id=" + Id +
                ", p_name='" + p_name + '\'' +
                ", details='" + details + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                ", createdTimestamp='" + createdTimestamp + '\'' +
                '}';
    }
}