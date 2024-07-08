package com.fucar.dao;

import com.fucar.pojo.Customer;
import java.util.List;

public interface ICustomerDAO {
    List<Customer> getCustomers();
    Customer findById(int customerID);
    Customer findByAccountId(int accountID);
    void save(Customer customer);
    void update(Customer customer);
    void delete(int customerID);
}