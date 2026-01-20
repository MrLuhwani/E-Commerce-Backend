package dev.luhwani.app.services.userServices;

import dev.luhwani.app.models.cartModel.Cart;
import dev.luhwani.app.models.cartModel.CartItem;
import dev.luhwani.app.models.productModels.Variant;
import dev.luhwani.app.models.userModels.Customer;
import dev.luhwani.app.repositories.CartRepo;

public class CartService {

    private CartRepo cartRepo;

    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    public void addNewCart(Customer customer, Cart cart) {
        cartRepo.getIdToCartMap().put(customer.getId(), cart);   
    }

    public void addToCart(Customer customer, Variant variant, int quantity) {
        CartItem cartItem = new CartItem(variant, quantity);
        getCart(customer).addCartItem(cartItem);
    }

    public boolean emptyCart(Customer customer) {
        return customer.getCart().getCartItems().isEmpty();
    }

    public Variant getVariant(CartItem cartItem) {
        return cartItem.getVariant();
    }

    public Integer getQuantity(CartItem cartItem) {
        return cartItem.getQuantity();
    }

    public Cart getCart(Customer customer) {
        return customer.getCart();
    }

}
