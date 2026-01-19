package dev.luhwani.app.applicationContext;

import dev.luhwani.app.services.CartService;
import dev.luhwani.app.services.CustomerService;

public class UserAppContext {
    
    CustomerService customerService;
    CartService cartService;

    public UserAppContext(CustomerService customerService, CartService cartService) {
        this.customerService = customerService;
        this.cartService = cartService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public CartService getCartService() {
        return cartService;
    }

}
