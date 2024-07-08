package com.fucar.dao;

import com.fucar.pojo.CarRental;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarRentalDAO implements ICarRentalDAO {

    private SessionFactory sessionFactory;

    public CarRentalDAO() {
        Configuration cf = new Configuration().configure("/hibernate.cfg.xml");
        sessionFactory = cf.buildSessionFactory();
    }

    @Override
    public void save(CarRental carRental) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(carRental);
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
    public void update(CarRental carRental) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(carRental);
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
    public void delete(int rentalID) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            CarRental carRental = session.get(CarRental.class, rentalID);
            if (carRental != null) {
                session.delete(carRental);
                t.commit();
                System.out.println("Successfully deleted");
            } else {
                t.rollback();
                System.out.println("CarRental not found with ID: " + rentalID);
            }
        } catch (Exception e) {
            t.rollback();
            System.out.println("Error " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public CarRental findById(int rentalID) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(CarRental.class, rentalID);
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<CarRental> findAll() {
        List<CarRental> carRentals = null;
        Session session = sessionFactory.openSession();
        try {
            carRentals = session.createQuery("FROM CarRental", CarRental.class).getResultList();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            session.close();
        }
        return carRentals;
    }

    @Override
    public List<CarRental> findByCustomerIdOrderByPickupDateDesc(int customerID) {
        List<CarRental> carRentals = null;
        Session session = sessionFactory.openSession();
        try {
            carRentals = session.createQuery("FROM CarRental WHERE customer.customerID = :customerID ORDER BY pickupDate DESC", CarRental.class)
                    .setParameter("customerID", customerID)
                    .getResultList();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            session.close();
        }
        return carRentals;
    }

    @Override
    public List<CarRental> findByPeriodDate(LocalDate startDate, LocalDate endDate) {
        List<CarRental> carRentals = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            carRentals = session.createQuery("FROM CarRental WHERE pickupDate BETWEEN :startDate AND :endDate ORDER BY pickupDate DESC", CarRental.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            session.close();
        }
        return carRentals;
    }

    @Override
    public boolean checkCarIsRent(int carID) {
        Session session = sessionFactory.openSession();
        Long count = null;
        try {
            count = session.createQuery("SELECT COUNT(c) FROM CarRental c WHERE c.car.carID = :carID", Long.class)
                    .setParameter("carID", carID)
                    .getSingleResult();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            session.close();
        }
        return count != null && count > 0;
    }

    @Override
    public boolean checkCarIsRentedInThisDate(int carID, LocalDate startDate, LocalDate endDate) {
        Session session = sessionFactory.openSession();
        Long count = null;
        try {
            count = session.createQuery("SELECT COUNT(c) FROM CarRental c WHERE c.car.carID = :carID AND " +
                            "(:startDate < c.returnDate AND :endDate > c.pickupDate)", Long.class)
                    .setParameter("carID", carID)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getSingleResult();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            session.close();
        }
        return count != null && count > 0;
    }


    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
