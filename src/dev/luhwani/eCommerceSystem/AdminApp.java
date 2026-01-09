package dev.luhwani.eCommerceSystem;

import java.util.Scanner;

import dev.luhwani.eCommerceSystem.services.AdminServices;

public class AdminApp {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        boolean running = true;
        while (running) {
            System.out.println("""
                    __WELCOME__
                    Enter the number for your choice:
                    1.Login
                    2.Register new admin user
                    3.Exit""");
            System.out.print("Response: ");
            String response = scanner.nextLine();
            switch (response) {
                case "1" -> AdminServices.adminLogin();
                case "2" -> System.out.println("new admin");
                case "3" -> {
                    running = false;
                    scanner.close();
                    System.out.println("Exitting...");
                }
            }
        }
    }
}
