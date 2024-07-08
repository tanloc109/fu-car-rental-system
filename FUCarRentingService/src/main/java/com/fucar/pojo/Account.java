package com.fucar.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountID")
    private int accountID;

    @Column(name = "AccountName", nullable = false, unique = true)
    private String accountName;

    @Column(name = "Role", nullable = false)
    private String role;

    public Account() {
    }

    public Account(String accountName, String role) {
        this.accountName = accountName;
        this.role = role;
    }

    public Account(int accountID, String accountName, String role) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.role = role;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return role == account.role && Objects.equals(accountID, account.accountID) && Objects.equals(accountName, account.accountName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID, accountName, role);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID='" + accountID + '\'' +
                ", accountName='" + accountName + '\'' +
                ", role=" + role +
                '}';
    }
}
