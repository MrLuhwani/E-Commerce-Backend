package dev.luhwani.app;

import java.util.Scanner;
import java.util.Objects;

import dev.luhwani.app.models.userModels.Customer;
import dev.luhwani.app.repositories.CustomerRepo;
import dev.luhwani.app.services.CartServices;
import dev.luhwani.app.services.ProductServices;
import dev.luhwani.app.services.UserServices;
import dev.luhwani.app.services.Utils;

public class UserApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        CustomerRepo customerRepo = new CustomerRepo();
        UserServices userServices = new UserServices(customerRepo);
        startApp();
    }

    private static void startApp() {
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
                    // Customer customer = UserServices.userLogin();
                    // if (!Objects.isNull(customer)) {
                    //     menu(customer);
                    // }
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

    private static void userLogin(CustomerRepo customerRepo) {
        String email;
        while (true) {
            System.out.print("Enter your email: ");
            email = scanner.nextLine().trim();
            if (Utils.validEmail(email)) {
                break;
            }
            System.out.println("Invalid email");
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
                    UserServices.emailToCustomerMap.get(customer.getPerson().getEmail()).setPassword(password);
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
