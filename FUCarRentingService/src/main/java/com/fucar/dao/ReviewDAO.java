package com.fucar.dao;

import com.fucar.pojo.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ReviewDAO implements IReviewDAO {
    private SessionFactory sessionFactory;

    public ReviewDAO() {
        Configuration cf = new Configuration().configure("/hibernate.cfg.xml");
        sessionFactory = cf.buildSessionFactory();
    }

    @Override
    public List<Review> getReviews() {
        List<Review> reviews = null;
        Session session = sessionFactory.openSession();
        try {
            reviews = session.createQuery("FROM Review", Review.class).getResultList();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            session.close();
        }
        return reviews;
    }

    @Override
    public Review findById(int reviewID) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Review.class, reviewID);
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void save(Review review) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(review);
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
    public void update(Review review) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(review);
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
    public void delete(int reviewID) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            Review review = session.get(Review.class, reviewID);
            if (review != null) {
                session.delete(review);
                t.commit();
                System.out.println("Successfully deleted");
            } else {
                t.rollback();
                System.out.println("Review not found with ID: " + reviewID);
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
