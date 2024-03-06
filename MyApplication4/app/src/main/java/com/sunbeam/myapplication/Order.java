package com.sunbeam.myapplication;

import java.io.Serializable;

public class Order implements Serializable {
    int id ;
    String p_name;
    int orderId;
    int p_Id;
    int quantity;
    float totalAmount;

    String timestamp;

    public Order(int id, String p_name,int orderId, int p_Id, int quantity, float totalAmount,String timestamp) {
        this.id = id;
        this.p_name=p_name;
        this.orderId = orderId;
        this.p_Id = p_Id;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.timestamp=timestamp;
    }


    public Order() {
    }



    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getP_Id() {
        return p_Id;
    }

    public void setP_Id(int p_Id) {
        this.p_Id = p_Id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", p_name='" + p_name + '\'' +
                ", orderId=" + orderId +
                ", p_Id=" + p_Id +
                ", quantity=" + quantity +
                ", totalAmount=" + totalAmount +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
