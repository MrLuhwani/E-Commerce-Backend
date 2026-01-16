package dev.luhwani.app.services;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import dev.luhwani.app.repositories.CustomerRepo;
import dev.luhwani.app.models.cartModel.Cart;
import dev.luhwani.app.models.userModels.Customer;
import dev.luhwani.app.models.userModels.Person;

public class UserServices {

    private CustomerRepo customerRepo;
    public UserServices(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public static Map<String, Customer> emailToCustomerMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    static List<Customer> customers = new ArrayList<>();

    public List<Customer> getCustomerList() {
        return customerRepo.customerList;
    }

    // public boolean emailExists(String email) {
    //     if(getEmailToCustomer().containsKey(email)) {
            
    //     }
    // }

    public Map<String, Customer> getEmailToCustomer() {
        return customerRepo.emailToCustomerMap;
    }
    public static Customer userLogin() {
        String email;
        while(true) {
            System.out.print("Enter your email: ");
            email = scanner.nextLine().trim();
            if (Utils.validEmail(email)) {break;}
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
            if (Utils.validPassword(password)) {break;}
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
                email = scanner.nextLine().trim();
                if (Utils.validEmail(email)) {
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
        Person person = new Person(firstName,lastName,email);
        Customer customer = new Customer(person, password);
        customer.setCart(new Cart());
        emailToCustomerMap.put(email, customer);
        customers.add(customer);
        return customer;
    }

}
