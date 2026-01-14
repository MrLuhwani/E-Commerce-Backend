package dev.luhwani.eCommerceSystem.cartModel;

import dev.luhwani.eCommerceSystem.productModels.Variant;

public class CartItem {
    private int itemId;
    private int quantity;
    private Cart cart;
    private Variant variant = null;
    public CartItem (Variant variant, int quantity) {
        this.variant = variant;
        this.itemId = variant.getId();
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Variant getVariant() {
        return variant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
