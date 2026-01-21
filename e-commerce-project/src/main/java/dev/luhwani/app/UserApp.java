package dev.luhwani.app;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dev.luhwani.app.applicationContext.UserAppContext;
import dev.luhwani.app.models.cartModel.CartItem;
import dev.luhwani.app.models.productModels.Category;
import dev.luhwani.app.models.productModels.Product;
import dev.luhwani.app.models.productModels.Variant;
import dev.luhwani.app.models.userModels.Customer;
import dev.luhwani.app.repositories.CartRepo;
import dev.luhwani.app.repositories.CustomerRepo;
import dev.luhwani.app.repositories.ProductRepo;
import dev.luhwani.app.services.Utils;
import dev.luhwani.app.services.productServices.ProductService;
import dev.luhwani.app.services.userServices.CartService;
import dev.luhwani.app.services.userServices.CustomerService;

public class UserApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        CustomerRepo customerRepo = new CustomerRepo();
        CartRepo cartRepo = new CartRepo();
        CartService cartService = new CartService(cartRepo);
        CustomerService customerService = new CustomerService(customerRepo, cartService);
        ProductRepo productRepo = new ProductRepo();
        ProductService productService = new ProductService(productRepo);
        UserAppContext userAppContext = new UserAppContext(customerService, cartService, productService);
        startApp(userAppContext);
    }

    private static void startApp(UserAppContext userAppContext) {
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
                    Customer customer = userLogin(userAppContext);
                    if (!Objects.isNull(customer)) {
                        menu(customer, userAppContext);
                    }
                }
                case "2" -> {
                    Customer customer = createAcct(userAppContext);
                    menu(customer, userAppContext);
                }
                case "3" -> {
                    running = false;
                    scanner.close();
                    System.out.println("Exitting...");
                }
            }
        }
    }

    private static Customer userLogin(UserAppContext userAppContext) {
        CustomerService customerService = userAppContext.getCustomerService();
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
            if (customerService.getPassword(email).equals(password)) {
                System.out.println("Welcome " + customerService.getName(email));
                return customer;
            }
            System.out.println("Invalid password");
        }
    }

    private static Customer createAcct(UserAppContext userAppContext) {
        CustomerService customerService = userAppContext.getCustomerService();
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
        System.out.println("Welcome " + customerService.getName(email));
        return customer;
    }

    private static void menu(Customer customer, UserAppContext userAppContext) {
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
                case "1" -> viewProducts(customer, userAppContext.getProductService(), userAppContext.getCartService());
                case "2" -> viewCart(customer, userAppContext.getCartService());
                case "3" -> changePassword(customer);
                case "4" -> {
                    running = false;
                    System.out.println("Logging Out...");
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private static void viewProducts(Customer customer, ProductService productService, CartService cartService) {
        int numOfCategories;
        if (productService.getCategories().isEmpty()) {
            System.out.println("No available categories");
            return;
        }
        List<Category> categories = productService.getCategories();
        numOfCategories = categories.size();
        int count = 1;
        for(Category category : categories) {
            System.out.println(count + "." + category.getName());
            count++;
        }
        String choice;
        while (true) {
            System.out.println("Enter the number of your choice: ");
            choice = scanner.nextLine().trim();
            if (Utils.validChoice(choice, numOfCategories)) {
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        int choiceInt = Integer.parseInt(choice);
        Category chosenCategory = categories.get(choiceInt - 1);
        List<Product> productsInCategory = productService.filterProductByCategory(chosenCategory.getId());
        if (productsInCategory.isEmpty()) {
            System.out.println("No available products yet");
            return;
        }
        List<Variant> activeVariants = new ArrayList<>();
        int numOfVariants = 0;
        for (Product product : productsInCategory) {
            for (Variant variant : productService.getVariants(product)) {
                if (productService.getIsActive(variant) && productService.getStock(variant) > 0) {
                    numOfVariants += 1;
                    System.out.println("product " + numOfVariants + ".");
                    System.out.println("_____________");
                    variant.getDetails();
                    activeVariants.add(variant);
                }
            }
        }
        while (true) {
            System.out.println("Enter the number of the product: ");
            choice = scanner.nextLine().trim();
            int size = activeVariants.size();
            if (Utils.validChoice(choice, size)) {
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        Variant chosenVariant = activeVariants.get(choiceInt - 1);
        while (true) {
            System.out.println("""
                    Enter number for your choice:
                    1.Buy product
                    2.Add to Cart
                    Response: """);
            choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> System.out.println("""
                        Purchase feature is not available yet
                        Please try again later
                        """);
                case "2" -> {
                    addToCart(customer, chosenVariant, cartService);
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private static void viewCart(Customer customer, CartService cartService) {
        if (cartService.emptyCart(customer)) {
            System.out.println("No items in cart");
            return;
        }
        int counter = 0;
        for (CartItem cartItem : cartService.getCart(customer).getCartItems()) {
            counter++;
            System.out.println("product " + counter + ".");
            System.out.println("___________");
            cartService.getVariant(cartItem).getDetails();
            System.out.print("Quantity: " + cartService.getQuantity(cartItem));
        }
        String choice;
        int choiceInt;
        while (true) {
            System.out.println("Enter the number of the product you wish to buy");
            System.out.println("or click 0 to exit");
            System.out.print("Response: ");
            choice = scanner.nextLine().trim();
            int size = cartService.getCart(customer).getCartItems().size();
            if (choice == "0") {
                System.out.println("Returning to menu...");
                return;
            } else if (Utils.validChoice(choice, size)) {
                choiceInt = Integer.parseInt(choice);
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        CartItem cartItem = cartService.getCart(customer).getCartItems().get(choiceInt - 1);
        Variant variant = cartItem.getVariant();
        //For now, no purchase function
        System.out.println("Purchase is currently not available. Please try again later");
        return;
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

    private static void addToCart(Customer customer, Variant variant, CartService cartService) {
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
        cartService.addToCart(customer, variant, quantity);
        System.out.println("product has been added to cart");
    }

}
