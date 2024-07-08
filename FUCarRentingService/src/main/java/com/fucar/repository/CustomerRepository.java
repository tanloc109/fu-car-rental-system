package com.fucar.repository;

import com.fucar.dao.CustomerDAO;
import com.fucar.pojo.Customer;
import java.util.List;

public class CustomerRepository implements ICustomerRepository {

    private static CustomerDAO customerDAO = null;

    public CustomerRepository() {
        if (customerDAO == null) {
            customerDAO = new CustomerDAO();
        }
    }

    @Override
    public List<Customer> findAll() {
        return customerDAO.getCustomers();
    }

    @Override
    public Customer findById(int customerID) {
        return customerDAO.findById(customerID);
    }

    @Override
    public Customer findByAccountId(int accountID) {
        return customerDAO.findByAccountId(accountID);
    }

    @Override
    public void save(Customer customer) {
        customerDAO.save(customer);
    }

    @Override
    public void update(Customer customer) {
        customerDAO.update(customer);
    }

    @Override
    public void delete(int customerID) {
        customerDAO.delete(customerID);
    }
}
