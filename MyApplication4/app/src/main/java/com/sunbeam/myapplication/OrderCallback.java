package com.sunbeam.myapplication;

import java.util.List;

public interface OrderCallback {
    void onOrdersLoaded(List<Order> orders);
    void onOrdersFailed(String errorMessage);
}
