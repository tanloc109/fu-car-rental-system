package com.fucar.repository;

import com.fucar.pojo.Customer;
import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();
    Customer findById(int customerID);
    Customer findByAccountId(int accountID);
    void save(Customer customer);
    void update(Customer customer);
    void delete(int customerID);
}
