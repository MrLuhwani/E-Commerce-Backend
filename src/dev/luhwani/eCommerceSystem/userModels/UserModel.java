package dev.luhwani.eCommerceSystem.userModels;

public abstract class UserModel {

    String firstName;
    String lastName;
    String email;
    int id;
    String password;
    Role role;
    UserModel (String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public int getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}