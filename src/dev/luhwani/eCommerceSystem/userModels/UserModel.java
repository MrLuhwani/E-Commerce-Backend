package dev.luhwani.eCommerceSystem.userModels;

public abstract class UserModel {

    private Person person = null;
    private String password;
    int id;

    UserModel (Person person, String password) {
        this.person = person;
        this.password = password;
    }
    public Person getPerson() {
        return person;
    }
    public int getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}