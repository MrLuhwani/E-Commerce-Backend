package dev.luhwani.app.applicationContext;

import dev.luhwani.app.services.adminServices.AdminProductService;
import dev.luhwani.app.services.adminServices.AdminService;

public class AdminAppContext {
    private AdminService adminService;
    private AdminProductService adminProductService;
    
    public AdminAppContext(AdminService adminService, AdminProductService adminProductService) {
        this.adminService = adminService;
        this.adminProductService = adminProductService;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public AdminProductService getAdminProductService() {
        return adminProductService;
    }
}
