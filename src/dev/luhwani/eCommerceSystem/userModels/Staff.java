package dev.luhwani.eCommerceSystem.userModels;

public class Staff {
    
    private Person person = null;
    //Unlike the other classes, some other system generates the worker Id
    //so no need for the count variable
    private int staffId;

    Staff(Person person, int staffId) {
        this.person = person;
        this.staffId = staffId;
    }

    public Person getPerson() {
        return person;
    }

    public int getStaffId() {
        return staffId;
    }
}