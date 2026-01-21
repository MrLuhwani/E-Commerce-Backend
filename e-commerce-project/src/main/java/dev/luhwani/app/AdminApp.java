package dev.luhwani.app;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

import dev.luhwani.app.applicationContext.AdminAppContext;
import dev.luhwani.app.models.productModels.Product;
import dev.luhwani.app.models.productModels.Variant;
import dev.luhwani.app.models.userModels.Admin;
import dev.luhwani.app.models.userModels.Staff;
import dev.luhwani.app.repositories.AdminRepo;
import dev.luhwani.app.repositories.ProductRepo;
import dev.luhwani.app.services.Utils;
import dev.luhwani.app.services.adminServices.AdminProductService;
import dev.luhwani.app.services.adminServices.AdminService;
import dev.luhwani.app.services.productServices.ProductService;

public class AdminApp {

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        AdminRepo adminRepo = new AdminRepo();
        AdminService adminService = new AdminService(adminRepo);
        ProductRepo productRepo = new ProductRepo();
        ProductService productService = new ProductService(productRepo);
        AdminAppContext adminAppContext = new AdminAppContext(adminService, productService);
        startApp(adminAppContext);
    }

    private static void startApp(AdminAppContext adminAppContext) {
        boolean running = true;
        while (running) {
            System.out.println("""
                    __WELCOME__
                    Enter the number for your choice:
                    1.Login
                    2.Register new admin user
                    3.Exit""");
            System.out.print("Response: ");
            String response = scanner.nextLine().trim();
            switch (response) {
                case "1" -> {
                    Admin admin = adminLogin(adminAppContext.getAdminService());
                    if (!Objects.isNull(admin)) {
                        menu(admin, adminAppContext);
                    }
                }
                case "2" -> {
                    Admin admin = registerAdmin(adminAppContext.getAdminService());
                    if (!Objects.isNull(admin)) {
                        menu(admin, adminAppContext);
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

    private static void menu(Admin admin, AdminAppContext adminAppContext) {
        String response;
        ProductService productService = adminAppContext.getProductService();
        boolean running = true;
        while (running) {
            System.out.println("""
                    Enter the number for your choice:
                    1.Add new product category
                    2.Add new product
                    3.Add new variant
                    4.Change Product Price
                    5.Edit product stock
                    6.Product temporary deactivation
                    7.Permanent product deletion
                    8.Change Password
                    9.Log Out""");
            System.out.print("Response: ");
            response = scanner.nextLine().trim();
            switch (response) {
                case "1" -> addNewCategory(productService);
                case "2" -> addNewProduct(productService);
                case "3" -> addProductVariant(productService);
                case "4" -> changeProductPrice(productService);
                case "5" -> AdminProductService.editProductStock();
                case "6" -> AdminProductService.temporaryDeactivation();
                case "7" -> AdminProductService.permanentDeactivation();
                case "8" -> changePassword(admin);
                case "9" -> {
                    running = false;
                    System.out.println("Logging Out...");
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private static Admin adminLogin(AdminService adminService) {
        String workId;
        Admin admin;
        while (true) {
            System.out.println("Enter your worker ID: ");
            workId = scanner.nextLine().trim();
            try {
                if (adminService.getIdToAdminMap().containsKey(Integer.parseInt(workId))) {
                    admin = adminService.getAdmin(Integer.parseInt(workId));
                    break;
                }
                System.out.println("ID not found");
                return null;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String password;
        System.out.println("Enter password: ");
        password = scanner.nextLine();
        if (adminService.getPassword(admin).equals(password)) {
            System.out.println("Welcome " + adminService.getName(Integer.parseInt(workId)));
            return admin;
        }
        System.out.println("Invalid password!");
        return null;
    }

    private static Admin registerAdmin(AdminService adminService) {
        String id;
        int idInt;
        Staff staff = null;
        while (true) {
            System.out.println("Enter your work ID: ");
            id = scanner.nextLine().trim();
            try {
                idInt = Integer.parseInt(id);
                if (adminService.getIdToAdminMap().containsKey(idInt)) {
                    System.out.println("This Id is already an admin");
                } else if (!adminService.getIdToStaffMap().containsKey(idInt)) {
                    System.out.println("ID not found");
                } else {
                    staff = adminService.getStaff(idInt);
                    break;
                }
                return null;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            System.out.println("Response: ");
            password = scanner.nextLine();   
            if (Utils.validPassword(password)) {
                break;
            }
            System.out.println("Invalid password");
        }
        adminService.registerAdmin(staff, password, idInt);
        System.out.println("Welcome " + adminService.getName(idInt));
        return adminService.getAdmin(idInt);
    }

    private static void addNewCategory(ProductService productService) {
        String name;
        while (true) {
            System.out.println("Enter new category: ");
            name = scanner.nextLine().trim().toLowerCase();
            if (productService.getNameToCategoryMap().containsKey(name)) {
                System.out.println("This category has already been created");
                return;
            } else {
                break;
            }
        }
        productService.addNewCategory(name);
        System.out.println("Category successfully created");
    }

    private static void addNewProduct(ProductService productService) {
        String name;
        while (true) {
            System.out.println("Enter product name: ");
            name = scanner.nextLine().trim().toLowerCase();
            if (productService.getNameToProductMap().containsKey(name)) {
                System.out.println("This product already exists");
                return;
            } else {
                break;
            }
        }
        String description;
        System.out.print("Enter product description: ");
        description = scanner.nextLine().trim();
        System.out.println("""
                How to Register Products Categories
                Example: 1.Jewelry 2.Tech 3.Accessory 4.Furniture
                If the product belongs to furniture and accessory category
                Your response: 3,4 (no space please)
                """);
        System.out.println("Available Product Categories:");
        int count = 0;
        for (String string : productService.getNameToCategoryMap().keySet()) {
            System.out.println(count + "." + string);
        }
        String productCategories;
        String[] categoriesArr = null;
        while (true) {
            boolean validCategories = true;
            System.out.print("Enter product categories: ");
            productCategories = scanner.nextLine().trim();
            Pattern pattern = Pattern.compile("^\\d+(,\\d+)*$");
            boolean validFormat = pattern.matcher(productCategories).matches();
            if (validFormat) {
                categoriesArr = productCategories.split(",");
                for (String string : categoriesArr) {
                    if (Integer.parseInt(string) > productService.getCategories().size()) {
                        System.out.printf("%s is invalid\n", string);
                        validCategories = false;
                    }
                }
            } else {
                validCategories = false;
                System.out.println("Invalid input");
            }
            if (validCategories) {
                break;
            }
        }
        productService.addNewProduct(name, description, categoriesArr);
        System.out.println("Product has successfuly been added to categories");
    }

    private static void addProductVariant(ProductService productService) {
        Product product = getProductChoice(productService);
        if (Objects.isNull(productService)) {
            return;
        }
        String variation;
        System.out.println("Enter variant features: ");
        variation = scanner.nextLine().trim();
        String nairaString;
        while (true) {
            System.out.print("Enter price of product in naira: ");
            nairaString = scanner.nextLine();
            try {
                Double.parseDouble(nairaString);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long priceInKobo = Utils.nairaStringToKobo(nairaString);
        String quantity;
        while (true) {
            System.out.print("Enter the stock quantity: ");
            quantity = scanner.nextLine().trim();
            if (!quantity.startsWith("-")) {
                try {
                    Integer.parseInt(quantity);
                    break;
                } catch (NumberFormatException e) {
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Invalid input");
            }
        }
        productService.addNewVariant(product, priceInKobo, variation, Integer.parseInt(quantity));
        System.out.println("Product is already active");
        System.out.println("Deactivate product if needed in the menu section");
    }
    
    private static void changeProductPrice(ProductService productService) {
        Product product = getProductChoice(productService);
        if (Objects.isNull(product)) {
            return;
        }
        int count = 0;
        for (Variant variant : product.getVariants()) {
            count++;
            System.out.println("product " + count + ".");
            System.out.println("_____________");
            variant.getDetails();
        }
        String choice = getVariantChoice(count);
        Variant variant = product.getVariants().get(Integer.parseInt(choice) - 1);
        String nairaString;
        while (true) {
            System.out.print("Enter the new price(₦): ");
            nairaString = scanner.nextLine().trim();
            try {
                Double.parseDouble(nairaString);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long kobo = Utils.nairaStringToKobo(nairaString);
        variant.setKoboPrice(kobo);
        System.out.println("Price updated successfully");
    }

    private static void changePassword(Admin admin) {
        String oldPassword = admin.getPassword();
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
        admin.setPassword(newPassword);
    }

    private static Product getProductChoice(ProductService productService) {
        String productName;
        while (true) {
            System.out.println("Enter product name: ");
            productName = scanner.nextLine().trim().toLowerCase();
            if (productService.getNameToProductMap().containsKey(productName)) {
                break;
            }
            System.out.println("Product not found");
            return null;
        }
        return productService.getNameToProductMap().get(productName);
    }

    private static String getVariantChoice(int count) {
        String choice;
        while (true) {
            System.out.print("Enter the number for the variant: ");
            choice = scanner.nextLine().trim().toLowerCase();
            try {
                if (Integer.parseInt(choice) > count || Integer.parseInt(choice) <= 0) {
                    System.out.println("Invalid choice");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return choice;
    }
}
