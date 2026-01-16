package dev.luhwani.app;

import java.util.Objects;
import java.util.Scanner;

import dev.luhwani.app.models.userModels.Admin;
import dev.luhwani.app.services.AdminProductServices;
import dev.luhwani.app.services.AdminServices;

public class AdminApp {

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        startApp();
    }

    private static void startApp() {
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
                    Admin admin = AdminServices.adminLogin();
                    if (!Objects.isNull(admin)) {
                        menu(admin);
                    }
                }
                case "2" -> {
                    Admin admin = AdminServices.adminLogin();
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
                case "1" -> AdminProductServices.addNewCategory();
                case "2" -> AdminProductServices.addNewProduct();
                case "3" -> AdminProductServices.addProductVariant();
                case "4" -> AdminProductServices.changeProductPrice();
                case "5" -> AdminProductServices.editProductStock();
                case "6" -> AdminProductServices.temporaryDeactivation();
                case "7" -> AdminProductServices.permanentDeactivation();
                case "8" -> {
                    running = false;
                    System.out.println("Logging Out...");
                }
                default -> System.out.println("Invalid input");
            }
        }
    }
}
