package com.fucar.dao;

import com.fucar.pojo.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class AccountDAO implements IAccountDAO {

    private SessionFactory sessionFactory;

    public AccountDAO() {
        Configuration cf = new Configuration().configure("/hibernate.cfg.xml");
        sessionFactory = cf.buildSessionFactory();
    }

    @Override
    public List<Account> getAccounts() {
        List<Account> accounts = null;
        Session session = sessionFactory.openSession();
        try {
            accounts = session.createQuery("FROM Account", Account.class).getResultList();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            session.close();
        }
        return accounts;
    }

    @Override
    public Account findById(int accountID) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Account.class, accountID);
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Account findByAccountName(String accountName) {
        Session session = sessionFactory.openSession();
        try {
            List<Account> accounts = session.createQuery("FROM Account WHERE accountName = :accountName", Account.class)
                    .setParameter("accountName", accountName)
                    .getResultList();
            if (accounts.isEmpty()) {
                return null;
            } else {
                return accounts.get(0);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        } finally {
            session.close();
        }
    }


    @Override
    public void save(Account account) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(account);
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
    public void update(Account account) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(account);
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
    public void delete(int accountID) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            Account account = session.get(Account.class, accountID);
            if (account != null) {
                session.delete(account);
                t.commit();
                System.out.println("Successfully deleted");
            } else {
                t.rollback();
                System.out.println("Account not found with ID: " + accountID);
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