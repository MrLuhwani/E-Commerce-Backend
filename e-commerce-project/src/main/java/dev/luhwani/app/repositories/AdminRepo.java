package dev.luhwani.app.repositories;

import java.util.HashMap;
import java.util.Map;

import dev.luhwani.app.models.userModels.Admin;
import dev.luhwani.app.models.userModels.Staff;

public class AdminRepo {
    private Map<Integer, Admin> workIdToAdminMap = new HashMap<>();
    // this is a map containing the staffs alone, but not staffs that are admins
    private Map<Integer, Staff> workIdToStaffMap = new HashMap<>();

    public Map<Integer, Admin> getIdToAdminMap() {
        return workIdToAdminMap;
    }

    public Map<Integer, Staff> getIdToStaffMap() {
        return workIdToStaffMap;
    }
}
