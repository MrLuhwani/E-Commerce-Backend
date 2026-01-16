package dev.luhwani.app.services;

import java.util.Scanner;

import dev.luhwani.app.models.cartModel.CartItem;
import dev.luhwani.app.models.productModels.Variant;
import dev.luhwani.app.models.userModels.Customer;

public class CartServices {

    static Scanner scanner = new Scanner(System.in);

    static void addToCart(Customer customer, Variant chosenVariant) {
        String choice;
        int quantity;
        while (true) {
            System.out.println("Enter the amount of the product\nto add to cart");
            System.out.print("Response: ");
            choice = scanner.nextLine().trim();
            try {
                quantity = Integer.parseInt(choice);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CartItem cartItem = new CartItem(chosenVariant, quantity);
        customer.getCart().addCartItem(cartItem);
        System.out.println("product has been added to cart");
    }

    public static void viewCart(Customer customer) {
        if (customer.getCart().getCartItems().isEmpty()) {
            System.out.println("No items in cart");
            return;
        }
        int counter = 0;
        for (CartItem cartItem : customer.getCart().getCartItems()) {
            counter++;
            System.out.println("product " + counter + ".");
            System.out.println("___________");
            cartItem.getVariant().getDetails();
            System.out.print("Quantity: " + cartItem.getQuantity());
        }
        String choice;
        while (true) {
            System.out.println("Enter the number of the product you wish to buy");
            System.out.println("or click 0 to exit");
            System.out.print("Response: ");
            choice = scanner.nextLine().trim();
            try {
                if (Integer.parseInt(choice) > customer.getCart().getCartItems().size() || Integer.parseInt(choice) < 0) {
                    System.out.println("Invalid choice");
                } else if (Integer.parseInt(choice) == 0) {
                    System.out.println("Returning to menu...");
                    return;
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CartItem cartItem = customer.getCart().getCartItems().get(Integer.parseInt(choice) - 1);
        Variant variant = cartItem.getVariant();
        //For now, no purchase function
        System.out.println("Purchase is currently not available. Please try again later");
        return;
    }
}
