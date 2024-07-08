package com.fucar.login;

import com.fucar.pojo.Account;
import com.fucar.pojo.Customer;
import com.fucar.service.AccountService;
import com.fucar.service.CustomerService;
import com.fucar.service.IAccountService;
import com.fucar.service.ICustomerService;

public class LoginService {

    private ICustomerService customerService;

    private IAccountService accountService;

    public boolean checkLogin(String accountName, String password) {
        Account account = accountService.findByAccountName(accountName);
        if (account != null) {
            Customer customer = customerService.findByAccountId(account.getAccountID());
            if (customer != null) {
                if (customer.getPassword().equals(password)) {
                	AccountLogin.curUserLogin = customer;
                    return true;
                }
            }
        }
        return false;
    }


    public LoginService() {
        if (customerService == null) {
            customerService = new CustomerService();
        }
        if (accountService == null) {
            accountService = new AccountService();
        }
    }

    public ICustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }

    public IAccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }
}
