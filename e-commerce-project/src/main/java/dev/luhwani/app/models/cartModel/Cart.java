package dev.luhwani.app.models.cartModel;

import java.util.ArrayList;
import java.util.List;

import dev.luhwani.app.models.userModels.Customer;

public class Cart {
    private int cartId;
    private List<CartItem> cartItems = new ArrayList<>();
    public void setCartId(Customer customer) {
        this.cartId = customer.getId();
    }
    
    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
