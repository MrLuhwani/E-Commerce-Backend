package dev.luhwani.eCommerceSystem.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import dev.luhwani.eCommerceSystem.userModels.Admin;

public class AdminServices {

    static Map<Integer, Admin> workIdToAdminMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static Admin adminLogin() {
        String workId;
        Admin admin;
        while (true) {
            System.out.println("Enter your worker ID: ");
            workId = scanner.nextLine();
            try {
                if (workIdToAdminMap.containsKey(Integer.parseInt(workId))) {
                    admin = workIdToAdminMap.get(Integer.parseInt(workId));
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
        if (admin.getPassword().equals(password)) {
            return admin;
        }
        System.out.println("Invalid password!");
        return null;
    }
}
