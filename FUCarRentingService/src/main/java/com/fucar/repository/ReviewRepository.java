package com.fucar.repository;

import com.fucar.dao.IReviewDAO;
import com.fucar.dao.ReviewDAO;
import com.fucar.pojo.Review;

import java.util.List;

public class ReviewRepository implements IReviewRepository {

    private static IReviewDAO reviewDAO = null;

    public ReviewRepository() {
        if (reviewDAO == null) {
            reviewDAO = new ReviewDAO();
        }
    }

    @Override
    public List<Review> findAll() {
        return reviewDAO.getReviews();
    }

    @Override
    public Review findById(int reviewID) {
        return reviewDAO.findById(reviewID);
    }

    @Override
    public void save(Review review) {
        reviewDAO.save(review);
    }

    @Override
    public void update(Review review) {
        reviewDAO.update(review);
    }

    @Override
    public void delete(int reviewID) {
        reviewDAO.delete(reviewID);
    }
}
