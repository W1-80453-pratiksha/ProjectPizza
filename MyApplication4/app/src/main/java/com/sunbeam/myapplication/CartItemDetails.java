package com.sunbeam.myapplication;

import java.io.Serializable;

public class CartItemDetails implements Serializable {
    private CartItem cartItem;
    private Pizza pizza;

    public CartItemDetails(CartItem cartItem, Pizza pizza) {
        this.cartItem = cartItem;
        this.pizza = pizza;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public Pizza getPizza() {
        return pizza;
    }
}
