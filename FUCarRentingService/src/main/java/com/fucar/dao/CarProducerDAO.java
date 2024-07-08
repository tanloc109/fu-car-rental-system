package com.fucar.dao;

import com.fucar.pojo.CarProducer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CarProducerDAO implements ICarProducerDAO {

    private static SessionFactory sessionFactory = null;

    public CarProducerDAO() {
        if (sessionFactory == null) {
            Configuration cf = new Configuration().configure("/hibernate.cfg.xml");
            sessionFactory = cf.buildSessionFactory();
        }
    }

    @Override
    public List<CarProducer> findAll() {
        List<CarProducer> carProducers = null;
        Session session = sessionFactory.openSession();
        try {
            carProducers = session.createQuery("FROM CarProducer", CarProducer.class).getResultList();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            session.close();
        }
        return carProducers;
    }

    @Override
    public CarProducer findById(int producerID) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(CarProducer.class, producerID);
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void save(CarProducer carProducer) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(carProducer);
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
    public void update(CarProducer carProducer) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(carProducer);
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
    public void delete(int producerID) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            CarProducer carProducer = session.get(CarProducer.class, producerID);
            if (carProducer != null) {
                session.delete(carProducer);
                t.commit();
                System.out.println("Successfully deleted");
            } else {
                t.rollback();
                System.out.println("CarProducer not found with ID: " + producerID);
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