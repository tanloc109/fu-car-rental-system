package com.fucar.repository;

import com.fucar.dao.AccountDAO;
import com.fucar.dao.IAccountDAO;
import com.fucar.pojo.Account;

import java.util.List;

public class AccountRepository implements IAccountRepository{

    private static IAccountDAO accountDAO = null;

    public AccountRepository() {
        if (accountDAO == null) {
            accountDAO = new AccountDAO();
        }
    }

    @Override
    public List<Account> findAll() {
        return accountDAO.getAccounts();
    }

    @Override
    public Account findById(int accountId) {
        return accountDAO.findById(accountId);
    }

    @Override
    public Account findByAccountName(String accountName) {
        return accountDAO.findByAccountName(accountName);
    }

    @Override
    public void save(Account account) {
        accountDAO.save(account);
    }

    @Override
    public void update(Account account) {
        accountDAO.update(account);
    }

    @Override
    public void delete(int accountId) {
        accountDAO.delete(accountId);
    }

}
