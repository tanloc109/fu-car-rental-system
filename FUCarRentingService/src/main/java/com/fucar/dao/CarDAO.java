package com.fucar.dao;

import com.fucar.pojo.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CarDAO implements ICarDAO {

    private SessionFactory sessionFactory;

    public CarDAO() {
        Configuration cf = new Configuration().configure("/hibernate.cfg.xml");
        sessionFactory = cf.buildSessionFactory();
    }

    @Override
    public List<Car> getCars() {
        List<Car> cars = null;
        Session session = sessionFactory.openSession();
        try {
            cars = session.createQuery("FROM Car", Car.class).getResultList();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            session.close();
        }
        return cars;
    }

    @Override
    public Car findById(int carID) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Car.class, carID);
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void save(Car car) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(car);
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
    public void update(Car car) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(car);
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
    public void delete(int carID) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            Car car = session.get(Car.class, carID);
            if (car != null) {
                session.delete(car);
                t.commit();
                System.out.println("Successfully deleted");
            } else {
                t.rollback();
                System.out.println("Car not found with ID: " + carID);
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