package dev.luhwani.eCommerceSystem.cartModel;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    int cartId;
    ///the purpose of this cartId is for ease of implementing persistence
    //later on
    List<CartItem> cartItems;
    public Cart(int cartId) {
        this.cartId = cartId;
        cartItems = new ArrayList<>();
    }
}
