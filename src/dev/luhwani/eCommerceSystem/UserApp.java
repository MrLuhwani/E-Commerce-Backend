package dev.luhwani.eCommerceSystem;

import java.util.Scanner;
import java.util.Objects;

import dev.luhwani.eCommerceSystem.services.CartServices;
import dev.luhwani.eCommerceSystem.services.ProductServices;
import dev.luhwani.eCommerceSystem.services.UserServices;
import dev.luhwani.eCommerceSystem.services.Utils;
import dev.luhwani.eCommerceSystem.userModels.Customer;

public class UserApp {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        boolean running = true;
        while (running) {
            System.out.println("""
                    __WELCOME__
                    Enter the number for your choice:
                    1.Login
                    2.Create New Acct
                    3.Exit""");
            System.out.print("Response: ");
            String response = scanner.nextLine().trim();
            switch (response) {
                case "1" -> {
                    Customer customer = UserServices.userLogin();
                    if (!Objects.isNull(customer)) {
                        menu(customer);
                    }
                }
                case "2" -> {
                    Customer customer = UserServices.createAcct();
                    menu(customer);
                }
                case "3" -> {
                    running = false;
                    scanner.close();
                    System.out.println("Exitting...");
                }
            }
        }
    }

    private static void menu(Customer customer) {
        String response;
        boolean running = true;
        while (running) {
            System.out.println("""
                    Enter the number for your choice:
                    1.View product categories
                    2.View Cart
                    3.Change password
                    4.Log Out""");
            System.out.print("Response: ");
            response = scanner.nextLine().trim();
            switch (response) {
                case "1" -> ProductServices.viewProducts(customer);
                case "2" -> CartServices.viewCart(customer);
                case "3" -> {
                    String password = Utils.changePassword(customer);
                    dev.luhwani.eCommerceSystem.services.UserServices.emailToCustomerMap
                            .get(customer.getPerson().getEmail()).setPassword(password);
                }
                case "4" -> {
                    running = false;
                    System.out.println("Logging Out...");
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

}
