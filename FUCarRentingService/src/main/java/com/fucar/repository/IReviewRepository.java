package com.fucar.repository;

import com.fucar.pojo.Review;
import java.util.List;

public interface IReviewRepository {
    List<Review> findAll();
    Review findById(int reviewID);
    void save(Review review);
    void update(Review review);
    void delete(int reviewID);
}
