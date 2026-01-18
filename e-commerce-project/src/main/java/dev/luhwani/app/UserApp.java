package dev.luhwani.app;

import java.util.Scanner;
import java.util.Objects;

import dev.luhwani.app.models.userModels.Customer;
import dev.luhwani.app.repositories.CustomerRepo;
import dev.luhwani.app.repositories.ProductRepo;
import dev.luhwani.app.services.CartService;
import dev.luhwani.app.services.ProductService;
import dev.luhwani.app.services.CustomerService;
import dev.luhwani.app.services.Utils;

public class UserApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        CustomerRepo customerRepo = new CustomerRepo();
        CustomerService customerService = new CustomerService(customerRepo);
        startApp(customerService);
    }

    private static void startApp(CustomerService customerService) {
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
                    Customer customer = userLogin(customerService);
                    if (!Objects.isNull(customer)) {
                        menu(customer, customerService);
                    }
                }
                case "2" -> {
                    Customer customer = createAcct(customerService);
                    menu(customer, customerService);
                }
                case "3" -> {
                    running = false;
                    scanner.close();
                    System.out.println("Exitting...");
                }
            }
        }
    }

    private static Customer userLogin(CustomerService customerService) {
        String email;
        while (true) {
            System.out.print("Enter your email: ");
            email = scanner.nextLine().trim();
            if (Utils.validEmail(email)) {
                break;
            }
            System.out.println("Invalid email");
        }
        Customer customer = null;
        if (!customerService.emailExists(email)) {
            System.out.println("Email not found");
            return customer;
        }
        customer = customerService.getEmailToCustomer().get(email);
        String password;
        while (true) {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            if (customer.getPassword().equals(password)) {
                System.out.println("Welcome " + customer.getPerson().getFirstName());
                return customer;
            }
            System.out.println("Invalid password");
        }
    }

    private static Customer createAcct(CustomerService customerService) {
        String email;
        while (true) {
            while (true) {
                System.out.print("Enter your email: ");
                email = scanner.nextLine().trim();
                if (Utils.validEmail(email)) {
                    break;
                }
                System.out.println("Invalid email");
            }
            if (!customerService.emailExists(email)) {
                break;
            }
            System.out.println("This email is already in use");
        }
        String password;
        while (true) {
            System.out.println("""
                    Set new Password:
                    Password requirements:
                    1.Between 7 - 20 characters long
                    2.No space
                    3.Contains both letters and numbers
                    4.Contains at least one symbol""");
            System.out.print("Response: ");
            password = scanner.nextLine();
            if (Utils.validPassword(password)) {
                break;
            }
            System.out.println("Invalid Password");
        }
        String firstName;
        while (true) {
            System.out.print("Enter your first name: ");
            firstName = scanner.nextLine().trim();
            if (Utils.validName(firstName)) {
                break;
            }
            System.out.println("Invalid name");
        }
        String lastName;
        while (true) {
            System.out.print("Enter your last name: ");
            lastName = scanner.nextLine().trim();
            if (Utils.validName(lastName)) {
                break;
            }
            System.out.println("Invalid name");
        }
        Customer customer = customerService.createNewCustomer(firstName, lastName, email, password);
        return customer;
    }

    private static void menu(Customer customer, CustomerService customerService) {
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
                case "1" -> ProductService.viewProducts(customer);
                case "2" -> CartService.viewCart(customer);
                case "3" -> changePassword(customer);
                case "4" -> {
                    running = false;
                    System.out.println("Logging Out...");
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private static void changePassword(Customer customer) {
        String oldPassword = customer.getPassword();
        while (true) {
            System.out.print("Enter your old password: ");
            String input = scanner.nextLine();
            if (input.equals(oldPassword)) {
                break;
            }
            System.out.println("Invalid Password");
        }
        String newPassword;
        while (true) {
            System.out.println("""
                    Set new Password:
                    Password requirements:
                    1.Between 7 - 20 characters long
                    2.No space
                    3.Contains both letters and numbers
                    4.Contains at least one symbol""");
            System.out.print("Response: ");
            newPassword = scanner.nextLine();
            if (Utils.validPassword(newPassword)) {
                break;
            }
            System.out.println("Invalid Password");
        }
        String confirmPassword;
        while (true) {
            System.out.println("Confirm new password: ");
            confirmPassword = scanner.nextLine();
            if (newPassword.equals(confirmPassword)) {
                break;
            }
            System.out.println("Password doesnt match");
        }
        customer.setPassword(newPassword);
    }
}
