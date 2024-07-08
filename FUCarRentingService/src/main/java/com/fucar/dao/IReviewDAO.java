package com.fucar.dao;

import com.fucar.pojo.Review;
import java.util.List;

public interface IReviewDAO {
    List<Review> getReviews();
    Review findById(int reviewID);
    void save(Review review);
    void update(Review review);
    void delete(int reviewID);
}
