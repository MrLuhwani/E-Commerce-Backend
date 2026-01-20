package dev.luhwani.app.repositories;

import java.util.HashMap;
import java.util.Map;

import dev.luhwani.app.models.cartModel.Cart;

public class CartRepo {
    
    private Map<Integer, Cart> idToCartMap = new HashMap<>();

    public Map<Integer, Cart> getIdToCartMap() {
        return idToCartMap;
    }
}
