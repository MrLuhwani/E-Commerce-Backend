package dev.luhwani.eCommerceSystem;

import java.util.Objects;
import java.util.Scanner;

import dev.luhwani.eCommerceSystem.services.AdminServices;
import dev.luhwani.eCommerceSystem.userModels.Admin;

public class AdminApp {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        boolean running = true;
        while (running) {
            System.out.println("""
                    __WELCOME__
                    Enter the number for your choice:
                    1.Login
                    2.Register new admin user
                    3.Exit""");
            System.out.print("Response: ");
            String response = scanner.nextLine();
            switch (response) {
                case "1" -> {
                    Admin admin = AdminServices.adminLogin();
                    if(!Objects.isNull(admin)) {
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
                    4.Edit product stock
                    5.Product temporary deactivation
                    6.Permanent product deletion
                    7.Log Out""");
            System.out.print("Response: ");
            response = scanner.nextLine();
            switch (response) {
                case "1" -> System.out.println("Add new category");
                case "2" -> System.out.println("Add new product");
                case "3" -> System.out.println("add product variant");
                case "4" -> System.out.println("edit product stock");
                case "5" -> System.out.println("edit product stock");
                case "6" -> System.out.println("edit product stock");
                case "7" -> {
                    running = false;
                    System.out.println("Logging Out...");
                }
                default -> System.out.println("Invalid input");
            }
        }
        /*
         * low stock static method alert
         * view customer details e.g name, email, then it is logged because stuff like
         * this
         * has legal stuff in real life
         * create an order time variablde
         * 
         */
    }
}
