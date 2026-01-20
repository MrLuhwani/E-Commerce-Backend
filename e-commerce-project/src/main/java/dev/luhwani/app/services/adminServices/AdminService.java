package dev.luhwani.app.services.adminServices;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import dev.luhwani.app.models.userModels.Admin;
import dev.luhwani.app.models.userModels.Staff;
import dev.luhwani.app.repositories.AdminRepo;

public class AdminService {

    private AdminRepo adminRepo;

    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Map<Integer, Admin> getIdToAdminMap() {
        return adminRepo.getIdToAdminMap();
    }

    public Map<Integer, Staff> getIdToStaffMap() {
        return adminRepo.getIdToStaffMap();
    }

    public Admin getAdmin(Integer id) {
        return getIdToAdminMap().get(id);
    }

    public Staff getStaff(Integer id) {
        return getIdToStaffMap().get(id);
    }
    static Map<Integer, Admin> workIdToAdminMap = new HashMap<>();
    static Map<Integer, Staff> workIdToStaffMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public String getPassword(Admin admin) {
        return admin.getPassword();
    }

    public void registerAdmin(Staff staff, String password, Integer id) {
        Admin admin = new Admin(staff, password);
        getIdToAdminMap().put(id, admin);
    }

    public String getName(Integer id) {
        return getIdToAdminMap().get(id).getPerson().getName();
    }

}
