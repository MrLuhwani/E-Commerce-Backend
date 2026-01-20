package dev.luhwani.app.services.userServices;

import java.util.List;
import java.util.Map;

import dev.luhwani.app.repositories.CustomerRepo;
import dev.luhwani.app.models.cartModel.Cart;
import dev.luhwani.app.models.userModels.Customer;
import dev.luhwani.app.models.userModels.Person;

public class CustomerService {

    private CustomerRepo customerRepo;
    private CartService cartService;
    
    public CustomerService(CustomerRepo customerRepo, CartService cartService) {
        this.customerRepo = customerRepo;
        this.cartService = cartService;
    }

    public List<Customer> getCustomerList() {
        return customerRepo.getCustomers();
    }

    public boolean emailExists(String email) {
        return getEmailToCustomer().containsKey(email);
    }

    public Map<String, Customer> getEmailToCustomer() {
        return customerRepo.getEmailToCustomerMap();
    }

    public Customer createNewCustomer(String firstName, String lastName, String email, String password) {
        Person person = new Person(firstName,lastName,email);
        Customer customer = new Customer(person, password);
        Cart cart = new Cart();
        customer.setCart(cart);
        getEmailToCustomer().put(email, customer);
        getCustomerList().add(customer);
        cartService.addNewCart(customer, cart);
        return customer;
    }

    public String getPassword(String email) {
        return getEmailToCustomer().get(email).getPassword();
    }

    public String getName(String email) {
        return getEmailToCustomer().get(email).getPerson().getName();
    }
}
