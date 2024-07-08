package com.fucar.dao;

import com.fucar.pojo.Account;
import com.fucar.pojo.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {

    private SessionFactory sessionFactory;

    public CustomerDAO() {
        Configuration cf = new Configuration().configure("/hibernate.cfg.xml");
        sessionFactory = cf.buildSessionFactory();
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = null;
        Session session = sessionFactory.openSession();
        try {
            customers = session.createQuery("FROM Customer", Customer.class).getResultList();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            session.close();
        }
        return customers;
    }

    @Override
    public Customer findById(int customerID) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Customer.class, customerID);
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Customer findByAccountId(int accountID) {
        Session session = sessionFactory.openSession();
        try {
            List<Customer> customers = session.createQuery("FROM Customer WHERE account.id = :accountID", Customer.class)
                    .setParameter("accountID", accountID)
                    .getResultList();
            if (customers.isEmpty()) {
                return null;
            } else {
                return customers.get(0);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void save(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(customer);
            t.commit();
            System.out.println("Successfully saved");
        } catch (Exception e) {
            t.rollback();
            System.out.println("Error " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(customer);
            t.commit();
            System.out.println("Successfully updated");
        } catch (Exception e) {
            t.rollback();
            System.out.println("Error " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int customerID) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            Customer customer = session.get(Customer.class, customerID);
            if (customer != null) {
                session.delete(customer);
                t.commit();
                System.out.println("Successfully deleted");
            } else {
                t.rollback();
                System.out.println("Customer not found with ID: " + customerID);
            }
        } catch (Exception e) {
            t.rollback();
            System.out.println("Error " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
