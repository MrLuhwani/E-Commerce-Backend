package dev.luhwani.app.services;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dev.luhwani.app.repositories.CustomerRepo;
import dev.luhwani.app.models.cartModel.Cart;
import dev.luhwani.app.models.userModels.Customer;
import dev.luhwani.app.models.userModels.Person;

public class CustomerService {

    private CustomerRepo customerRepo;
    
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<Customer> getCustomerList() {
        return customerRepo.customers;
    }

    public boolean emailExists(String email) {
        return getEmailToCustomer().containsKey(email);
    }

    public Map<String, Customer> getEmailToCustomer() {
        return customerRepo.emailToCustomerMap;
    }

    public Customer createNewCustomer(String firstName, String lastName, String email, String password) {
        Person person = new Person(firstName,lastName,email);
        Customer customer = new Customer(person, password);
        customer.setCart(new Cart());
        getEmailToCustomer().put(email, customer);
        getCustomerList().add(customer);
        return customer;
    }
}
