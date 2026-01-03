package dev.luhwani.eCommerceSystem;

import java.util.Scanner;

import dev.luhwani.eCommerceSystem.services.UserServices;

public class UserApp {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("""
                        __WELCOME__
                        Enter the number for your choice:
                        1.Login
                        2.Create New Acct
                        3.Exit""");
                System.out.print("Response: ");
                String response = scanner.nextLine();
                switch (response) {
                    case "1" -> System.out.println("User login");
                    case "2" -> UserServices.createAcct();
                    case "3" -> {
                        running = false;
                        System.out.println("Exitting...");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
 * Login
 * 
 * Create acct
 * 
 * Exit
 */