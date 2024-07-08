package com.fucar.dao;

import com.fucar.pojo.Account;

import java.util.List;

public interface IAccountDAO {

    List<Account> getAccounts();
    Account findById(int accountID);
    Account findByAccountName(String accountName);
    void save(Account account);
    void update(Account account);
    void delete(int accountID);
}
