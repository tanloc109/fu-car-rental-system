package com.fucar.service;

import com.fucar.pojo.Review;
import java.util.List;

public interface IReviewService {
    List<Review> findAll();
    Review findById(int reviewID);
    void save(Review review);
    void update(Review review);
    void delete(int reviewID);
}
