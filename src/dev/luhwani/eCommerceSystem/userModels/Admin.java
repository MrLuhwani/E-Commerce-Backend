package dev.luhwani.eCommerceSystem.userModels;

public class Admin extends UserModel{
    
    static int counter = 0;
    
    public Admin(String firstName, String lastName, String email, String password) {
        counter ++;
        super(firstName,lastName,email,password,Role.ADMIN);
        this.id = counter;
    }
}
