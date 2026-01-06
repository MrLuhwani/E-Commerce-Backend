package dev.luhwani.eCommerceSystem.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

import dev.luhwani.eCommerceSystem.product.Category;
import dev.luhwani.eCommerceSystem.product.Product;
import dev.luhwani.eCommerceSystem.product.Variant;
import dev.luhwani.eCommerceSystem.userModels.Customer;

public class UserServices {

    static Map<String, Customer> emailToCustomerMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

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
            boolean found = false;
            for (String emailString : emailToCustomerMap.keySet()) {
                if (emailString.equals(email)) {
                    found = true;
                }
            }
            if (!found) {
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
        emailToCustomerMap.put(email, customer);
        return customer;
    }

    public static void menu(Customer customer) {
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
            response = scanner.nextLine();
            switch (response) {
                case "1" -> viewProducts();
                case "2" -> {}
                case "3" -> changePassword(customer);
                case "4" -> {
                    running = false;
                    System.out.println("Logging Out...");
                }
                default -> System.out.println("Invalid input");
            }
        }
        /*
         * user can view products based on their cateory:
         * user can check the variant
         * user can purchase product
         * User can add to cart
         * user can view cart
         */
    }

    private static void viewProducts() {
        int numOfCategories;
        List<Product> productsInCategory;
        if (ProductServices.categories.isEmpty()) {
            System.out.println("No available categories");
            return;
        }
        numOfCategories = ProductServices.categories.size();
        ProductServices.printCategories();
        boolean hasProducts = true;
        while (true) {
            try {
                int choice;
                System.out.println("Enter the number of your choice: ");
                choice = scanner.nextInt();
                if (choice > numOfCategories || choice < 1) {
                    System.out.println("Invalid choice");   
                } else {
                    choice -=1;
                    Category chosenCategory = ProductServices.categories.get(choice);
                    productsInCategory = ProductServices.filterProductByCategory(chosenCategory.getId());
                    if (Objects.isNull(productsInCategory)) {
                        System.out.println("No product available in this category yet");
                        hasProducts = false;
                    }
                    break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Invalid input");
                //Now Im wondering, if the user inputs a double, what happens
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!hasProducts) {return;}
        List<Variant> activeVariants = new ArrayList<>();
        int numOfVariants = 0;
        for (Product product : productsInCategory) {
            for (Variant variant : product.getVariants()) {
                if (variant.getIsActive() && variant.getStock() > 0) {
                numOfVariants += 1;
                System.out.println("Product "+ numOfVariants+".");
                System.out.println("_____________");
                variant.showDetails();
                activeVariants.add(variant);
                }
            }
        }
        while (true) {
            try {
                System.out.println("Enter the product you wish to buy: ");
                int choice = scanner.nextInt();
                //to be continued...       
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Invalid input");
            } catch (Exception e) {
                e.printStackTrace();
            }    
        }
        /*
         * for each member of the list, we print their toString i.e name, price, ....
         * then they select a product, but the issue is how do we know the exact product
         * they selected hmm...
         * then we ask if they want to buy, check variants or add to cart
         * buy... we can just tell them that purchase services not available
         * for variants,... ill think about it
         * for cart, we ask them the amount of the product they wish to add
         * to cart, then we get the product id and add to users cart
         */
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
