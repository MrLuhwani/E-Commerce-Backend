package dev.luhwani.eCommerceSystem.userModels;

public class Admin extends UserModel{
    
    public Admin(Staff staff, String password) {
        super(staff.getPerson(),password);
        this.id = staff.getStaffId();
    }
}
