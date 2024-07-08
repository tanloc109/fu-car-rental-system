package com.fucar.repository;

import com.fucar.pojo.Account;

import java.util.List;

public interface IAccountRepository {
    List<Account> findAll();
    Account findById(int accountId);
    Account findByAccountName(String accountName);
    void save(Account account);
    void update(Account account);
    void delete(int accountId);
}
