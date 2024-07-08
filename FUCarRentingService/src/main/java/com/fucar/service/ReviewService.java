package com.fucar.service;

import com.fucar.pojo.Review;
import com.fucar.repository.IReviewRepository;
import com.fucar.repository.ReviewRepository;

import java.util.List;

public class ReviewService implements IReviewService {

    private static IReviewRepository reviewRepository = null;

    public ReviewService() {
        if (reviewRepository == null) {
            reviewRepository = new ReviewRepository();
        }
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findById(int reviewID) {
        return reviewRepository.findById(reviewID);
    }

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public void update(Review review) {
        reviewRepository.update(review);
    }

    @Override
    public void delete(int reviewID) {
        reviewRepository.delete(reviewID);
    }
}
