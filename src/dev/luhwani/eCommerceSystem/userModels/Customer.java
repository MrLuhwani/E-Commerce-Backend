package dev.luhwani.eCommerceSystem.userModels;

import dev.luhwani.eCommerceSystem.cartModel.Cart;

public class Customer extends UserModel {
    private static int counter = 0;
    private Cart cart = null;
    public Customer(String firstName, String lastName, String email, String password) {
        counter++;
        super(firstName,lastName,email,password,Role.CUSTOMER);
        this.id = counter;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
        cart.setCartId(this);
    }
    public Cart getCart() {
        return cart;
    }
}
