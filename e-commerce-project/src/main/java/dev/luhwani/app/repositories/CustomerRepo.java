package dev.luhwani.app.repositories;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.luhwani.app.models.userModels.Customer;

public class CustomerRepo {
    public List<Customer> customerList = new ArrayList<>();
    public Map<String, Customer> emailToCustomerMap = new HashMap<>();
    
}