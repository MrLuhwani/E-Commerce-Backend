package dev.luhwani.app.repositories;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.luhwani.app.models.userModels.Customer;

public class CustomerRepo {
    private List<Customer> customers = new ArrayList<>();
    private Map<String, Customer> emailToCustomerMap = new HashMap<>();
   
    public List<Customer> getCustomers() {
        return customers;
    }

    public Map<String, Customer> getEmailToCustomerMap() {
        return emailToCustomerMap;
    }

}