package com.abhi_app.ReviewMS.ReviewService;


import com.abhi_app.ReviewMS.Entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    boolean createReviews(Review review);

    Review getReviewById(Long id);

    boolean updateReview(Long reviewId, Review review);

    boolean deleteReview(Long reviewId);

}
