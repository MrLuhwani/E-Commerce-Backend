package dev.luhwani.app.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import dev.luhwani.app.models.userModels.Admin;
import dev.luhwani.app.models.userModels.Staff;

public class AdminService {

    static Map<Integer, Admin> workIdToAdminMap = new HashMap<>();
    // this is a map containing the staffs alone, but not staffs that are admins
    static Map<Integer, Staff> workIdToStaffMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static Admin adminLogin() {
        String workId;
        Admin admin;
        while (true) {
            System.out.println("Enter your worker ID: ");
            workId = scanner.nextLine().trim();
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

    public static Admin registerAdmin() {
        String id;
        int idInt;
        Staff staff = null;
        while (true) {
            System.out.println("Enter your work ID: ");
            id = scanner.nextLine().trim();
            try {
                idInt = Integer.parseInt(id);
                if (workIdToAdminMap.containsKey(idInt)) {
                    System.out.println("This Id is already an admin");
                } else if (!workIdToStaffMap.containsKey(idInt)) {
                    System.out.println("ID not found");
                } else {
                    staff = workIdToStaffMap.get(idInt);
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
        Admin admin = new Admin(staff, password);
        workIdToAdminMap.put(idInt, admin);
        return admin;
    }

}
