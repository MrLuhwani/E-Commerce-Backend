package dev.luhwani.app;

import java.util.Objects;
import java.util.Scanner;

import dev.luhwani.app.applicationContext.AdminAppContext;
import dev.luhwani.app.models.userModels.Admin;
import dev.luhwani.app.repositories.AdminRepo;
import dev.luhwani.app.services.adminServices.AdminProductService;
import dev.luhwani.app.services.adminServices.AdminService;

public class AdminApp {

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        AdminRepo adminRepo = new AdminRepo();
        AdminService adminService = new AdminService(adminRepo);
        AdminProductService adminProductService = new AdminProductService();
        AdminAppContext adminAppContext = new AdminAppContext(adminService, adminProductService);
        startApp(adminAppContext);
    }

    private static void startApp(AdminAppContext adminAppContext) {
        boolean running = true;
        while (running) {
            System.out.println("""
                    __WELCOME__
                    Enter the number for your choice:
                    1.Login
                    2.Register new admin user
                    3.Exit""");
            System.out.print("Response: ");
            String response = scanner.nextLine().trim();
            switch (response) {
                case "1" -> {
                    Admin admin = adminLogin(adminAppContext.getAdminService());
                    if (!Objects.isNull(admin)) {
                        menu(admin);
                    }
                }
                case "2" -> {
                    Admin admin = AdminService.adminLogin();
                    if (!Objects.isNull(admin)) {
                        menu(admin);
                    }
                }
                case "3" -> {
                    running = false;
                    scanner.close();
                    System.out.println("Exitting...");
                }
            }
        }
    }

    private static void menu(Admin admin) {
        String response;
        boolean running = true;
        while (running) {
            System.out.println("""
                    Enter the number for your choice:
                    1.Add new product category
                    2.Add new product
                    3.Add new variant
                    4.Change Product Price
                    5.Edit product stock
                    6.Product temporary deactivation
                    7.Permanent product deletion
                    8.Log Out""");
            System.out.print("Response: ");
            response = scanner.nextLine().trim();
            switch (response) {
                case "1" -> AdminProductService.addNewCategory();
                case "2" -> AdminProductService.addNewProduct();
                case "3" -> AdminProductService.addProductVariant();
                case "4" -> AdminProductService.changeProductPrice();
                case "5" -> AdminProductService.editProductStock();
                case "6" -> AdminProductService.temporaryDeactivation();
                case "7" -> AdminProductService.permanentDeactivation();
                case "8" -> {
                    running = false;
                    System.out.println("Logging Out...");
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private static Admin adminLogin(AdminService adminService) {
        String workId;
        Admin admin;
        while (true) {
            System.out.println("Enter your worker ID: ");
            workId = scanner.nextLine().trim();
            try {
                if (adminService.getIdToAdminMap().containsKey(Integer.parseInt(workId))) {
                    admin = adminService.getAdmin(Integer.parseInt(workId));
                    break;
                }
                System.out.println("ID not found");
                return null;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String password;
        System.out.println("Enter password: ");
        password = scanner.nextLine();
        if (adminService.getPassword(admin).equals(password)) {
            return admin;
        }
        System.out.println("Invalid password!");
        return null;
    }
}
