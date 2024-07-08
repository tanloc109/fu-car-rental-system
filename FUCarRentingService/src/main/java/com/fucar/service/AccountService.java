package com.fucar.service;

import com.fucar.pojo.Account;
import com.fucar.repository.AccountRepository;
import com.fucar.repository.IAccountRepository;

import java.util.List;

public class AccountService implements IAccountService {

    private static IAccountRepository accountRepository= null;

    public AccountService() {
        if (accountRepository == null) {
            accountRepository = new AccountRepository();
        }
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(int accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public Account findByAccountName(String accountName) {
        return accountRepository.findByAccountName(accountName);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void update(Account account) {
        accountRepository.update(account);
    }

    @Override
    public void delete(int accountId) {
        accountRepository.delete(accountId);
    }
}
