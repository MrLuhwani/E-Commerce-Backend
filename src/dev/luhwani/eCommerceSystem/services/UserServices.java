package dev.luhwani.eCommerceSystem.services;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import dev.luhwani.eCommerceSystem.cartModel.Cart;
import dev.luhwani.eCommerceSystem.userModels.Customer;

public class UserServices {

    static Map<String, Customer> emailToCustomerMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    static List<Customer> customers = new ArrayList<>();

    public static Customer userLogin() {
        String email;
        while(true) {
            System.out.print("Enter your email: ");
            email = scanner.nextLine();
            if (validEmail(email)) {break;}
            System.out.println("Invalid email");
        }
        Customer customer = null;
        if (emailToCustomerMap.containsKey(email)) {
            customer = emailToCustomerMap.get(email);
        } else {
            System.out.println("Email not found");
            return customer;
        }
        String password;
        while (true) {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            if (validPassword(password)) {break;}
            System.out.println("Invalid password");
        }
        if (!customer.getPassword().equals(password)){
            System.out.println("Password does not match");
            return null;
        }
        System.out.println("Welcome...");
        return customer;
    }

    public static Customer createAcct() {
        String email;
        while (true) {
            while (true) {
                System.out.print("Enter your email: ");
                email = scanner.nextLine();
                if (validEmail(email)) {
                    break;
                }
                System.out.println("Invalid email");
            }
            if (!emailToCustomerMap.containsKey(email)) {
                break;
            }
            System.out.println("This email is already in use");
            // When you learn that aspect, send email verification OTP
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
            if (validPassword(password)) {
                break;
            }
            System.out.println("Invalid Password");
        }
        String firstName;
        while (true) {
            System.out.print("Enter your first name: ");
            firstName = scanner.nextLine();
            if (validName(firstName)) {
                break;
            }
            System.out.println("Invalid name");
        }
        String lastName;
        while (true) {
            System.out.print("Enter your last name: ");
            lastName = scanner.nextLine();
            if (validName(lastName)) {
                break;
            }
            System.out.println("Invalid name");
        }
        Customer customer = new Customer(firstName, lastName, email, password);
        customer.setCart(new Cart());
        emailToCustomerMap.put(email, customer);
        customers.add(customer);
        return customer;
    }

    private static boolean validEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9]+@[A-za-z-]+\\.[A-Za-z]{2,}$");
        return emailPattern.matcher(email).matches();
    }

    private static boolean validPassword(String password) {
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[^A-Za-z0-9\\s])[^\\s]{7,20}$");
        return passwordPattern.matcher(password).matches();
    }

    private static boolean validName(String name) {
        Pattern namePattern = Pattern.compile("^[A-Za-z]{2,}$");
        return namePattern.matcher(name).matches();
    }

    public static void changePassword(Customer customer) {
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
            if (validPassword(newPassword)) {
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
        emailToCustomerMap.get(customer.getEmail()).setPassword(newPassword);
    }
}
