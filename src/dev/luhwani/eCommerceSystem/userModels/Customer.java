package dev.luhwani.eCommerceSystem.userModels;

import dev.luhwani.eCommerceSystem.cartModel.Cart;

public class Customer extends UserModel {
    //coment counter if you wish to readjust
    static int counter = 0;
    Cart cart = null;
    public Customer(String firstName, String lastName, String email, String password) {
        counter++;
        super(firstName,lastName,email,password,Role.CUSTOMER);
        this.id = counter;
        //Need to refactor later, not following DI principles for cart
        this.cart = new Cart(this.getId());
    }
}
