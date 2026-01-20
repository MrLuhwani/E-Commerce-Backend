package dev.luhwani.app.services.adminServices;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import dev.luhwani.app.models.userModels.Admin;
import dev.luhwani.app.models.userModels.Staff;
import dev.luhwani.app.repositories.AdminRepo;
import dev.luhwani.app.services.Utils;

public class AdminService {

    private AdminRepo adminRepo;

    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Map<Integer, Admin> getIdToAdminMap() {
        return adminRepo.getIdToAdminMap();
    }

    public Admin getAdmin(Integer id) {
        return getIdToAdminMap().get(id);
    }
    static Map<Integer, Admin> workIdToAdminMap = new HashMap<>();
    // this is a map containing the staffs alone, but not staffs that are admins
    static Map<Integer, Staff> workIdToStaffMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public String getPassword(Admin admin) {
        return admin.getPassword();
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
