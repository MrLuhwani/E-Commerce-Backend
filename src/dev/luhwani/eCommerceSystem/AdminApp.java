package dev.luhwani.eCommerceSystem;

import java.util.Scanner;

public class AdminApp {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("""
                        __AWELCOME__
                        Enter the number for your choice:
                        1.Login
                        2.Create New Admin
                        3.Exit""");
                System.out.print("Response: ");
                String response = scanner.nextLine();
                switch (response) {
                    case "1" -> System.out.println("Admin login");
                    case "2" -> System.out.println("Create Admin");
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
