package dev.luhwani.app.applicationContext;

import dev.luhwani.app.services.adminServices.AdminService;
import dev.luhwani.app.services.productServices.ProductService;

public class AdminAppContext {
    private AdminService adminService;
    private ProductService productService;
    
    public AdminAppContext(AdminService adminService, ProductService productService) {
        this.adminService = adminService;
        this.productService = productService;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public ProductService getProductService() {
        return productService;
    }
}
