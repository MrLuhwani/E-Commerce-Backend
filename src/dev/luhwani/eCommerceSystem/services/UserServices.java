package dev.luhwani.eCommerceSystem.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import dev.luhwani.eCommerceSystem.userModels.Customer;

public class UserServices {

    static Map<String, Customer> gmailToCustomerMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void createAcct() {
        String email;
        while (true) {
            System.out.print("Enter your email: ");
            email = scanner.nextLine();
            if (validEmail(email)) {break;}
            // When you learn that aspect, send email verification OTP
            System.out.println("Invalid email");
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
            if (validPassword(password)) {break;}
            System.out.println("Invalid Password");
        }
        String firstName;
        while (true) {
            System.out.print("Enter your first name: ");
            firstName = scanner.nextLine();
            if (validName(firstName)) {break;}
            System.out.println("Invalid name");
        }
        String lastName;
        while (true) {
            System.out.print("Enter your last name: ");
            lastName = scanner.nextLine();
            if (validName(lastName)) {break;}
            System.out.println("Invalid name");
        }
        gmailToCustomerMap.put(email, new Customer(firstName, lastName, email, password));
    }

    private static boolean validEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9]+@[A-za-z-]+\\.[A-Za-z]{2,}$");
        return emailPattern.matcher(email).matches();
    }

    private static boolean validPassword(String password) {
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z0-9])(?=.*[^A-Za-z0-9\s])[^\s]{7,20}$");
        return passwordPattern.matcher(password).matches();
    }

    private static boolean validName(String name) {
        Pattern namePattern = Pattern.compile("^[A-Za-z]$");
        return namePattern.matcher(name).matches();
    }
}
