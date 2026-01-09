package dev.luhwani.eCommerceSystem.userModels;

public class Admin extends UserModel{
    
    static int counter = 0;
    int workId;
    //it might not actually be useful sha, but I'll see where it
    //can be used
    public Admin(String firstName, String lastName, String email, String password, int workId) {
        counter ++;
        super(firstName,lastName,email,password,Role.ADMIN);
        this.id = counter;
        this.workId = workId;
    }
}
