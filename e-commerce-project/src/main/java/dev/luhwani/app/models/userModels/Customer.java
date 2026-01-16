package dev.luhwani.app.models.userModels;

import dev.luhwani.app.models.cartModel.Cart;

public class Customer extends UserModel {
    private static int counter = 0;
    private Cart cart = null;
    public Customer(Person person, String password) {
        super(person, password);
        counter++;
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
