package com.fucar.pojo;

import javax.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private int customerID;

    @Column(name = "CustomerName", nullable = false)
    private String customerName;

    @Column(name = "Mobile", nullable = false)
    private String mobile;

    @Column(name = "Birthday", nullable = false)
    private Date birthday;

    @Column(name = "IdentityCard", nullable = false)
    private String identityCard;

    @Column(name = "LicenceNumber", nullable = false)
    private String licenceNumber;

    @Column(name = "LicenceDate", nullable = false)
    private Date licenceDate;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "AccountID", nullable = false)
    private Account account;

    public Customer() {
    }
    
    public Customer(String customerName, String mobile, Date birthday, String identityCard, String licenceNumber, Date licenceDate, String email, String password) {
        this.customerName = customerName;
        this.mobile = mobile;
        this.birthday = birthday;
        this.identityCard = identityCard;
        this.licenceNumber = licenceNumber;
        this.licenceDate = licenceDate;
        this.email = email;
        this.password = password;
    }

    public Customer(String customerName, String mobile, Date birthday, String identityCard, String licenceNumber, Date licenceDate, String email, String password, Account account) {
        this.customerName = customerName;
        this.mobile = mobile;
        this.birthday = birthday;
        this.identityCard = identityCard;
        this.licenceNumber = licenceNumber;
        this.licenceDate = licenceDate;
        this.email = email;
        this.password = password;
        this.account = account;
    }

    public Customer(int customerID, String customerName, String mobile, Date birthday, String identityCard, String licenceNumber, Date licenceDate, String email, String password, Account account) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.mobile = mobile;
        this.birthday = birthday;
        this.identityCard = identityCard;
        this.licenceNumber = licenceNumber;
        this.licenceDate = licenceDate;
        this.email = email;
        this.password = password;
        this.account = account;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public Date getLicenceDate() {
        return licenceDate;
    }

    public void setLicenceDate(Date licenceDate) {
        this.licenceDate = licenceDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerID == customer.customerID && Objects.equals(customerName, customer.customerName) && Objects.equals(mobile, customer.mobile) && Objects.equals(birthday, customer.birthday) && Objects.equals(identityCard, customer.identityCard) && Objects.equals(licenceNumber, customer.licenceNumber) && Objects.equals(licenceDate, customer.licenceDate) && Objects.equals(email, customer.email) && Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, customerName, mobile, birthday, identityCard, licenceNumber, licenceDate, email, password);
    }

    @Override
    public String toString() {
        return customerID + " | " + customerName + " | " + birthday + " | " + mobile;
    }
}
