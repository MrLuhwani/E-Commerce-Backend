package dev.luhwani.app.applicationContext;

import dev.luhwani.app.services.productServices.ProductService;
import dev.luhwani.app.services.userServices.CartService;
import dev.luhwani.app.services.userServices.CustomerService;

public class UserAppContext {
    
    private CustomerService customerService;
    private CartService cartService;
    private ProductService productService;

    public UserAppContext(CustomerService customerService, CartService cartService, ProductService productService) {
        this.customerService = customerService;
        this.cartService = cartService;
        this.productService = productService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public CartService getCartService() {
        return cartService;
    }

    public ProductService getProductService() {
        return productService;
    }

}
