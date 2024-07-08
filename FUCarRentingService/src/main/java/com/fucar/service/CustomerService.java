package com.fucar.service;

import com.fucar.pojo.Customer;
import com.fucar.repository.CustomerRepository;
import com.fucar.repository.ICustomerRepository;
import java.util.List;

public class CustomerService implements ICustomerService {

    private ICustomerRepository customerRepository;

    public CustomerService() {
        customerRepository = new CustomerRepository();
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int customerID) {
        return customerRepository.findById(customerID);
    }

    @Override
    public Customer findByAccountId(int accountID) {
        return customerRepository.findByAccountId(accountID);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        customerRepository.update(customer);
    }

    @Override
    public void delete(int customerID) {
        customerRepository.delete(customerID);
    }
}
