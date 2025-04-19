package com.abhi_app.ReviewMS.ReviewService;


import com.abhi_app.ReviewMS.Entity.Review;
import com.abhi_app.ReviewMS.Exceptions.ReviewNotFoundException;
import com.abhi_app.ReviewMS.ReviewRepository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImple implements ReviewService{

    @Autowired
    ReviewRepository reviewRepository;


    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean createReviews(Review review) {

        if(review != null){
            try{
                reviewRepository.save(review);
                return true;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review exception is not found"));
    }

    @Override
    public boolean updateReview(Long reviewId, Review review) {

        Review oldReview = reviewRepository.findById(reviewId).orElse(null);
        if(oldReview != null){

            if(review.getTitle() != null){
                oldReview.setTitle(review.getTitle());
            }
            if(review.getDescription() != null){
                oldReview.setDescription(review.getDescription());
            }
            if(review.getRating() != 0.0){
                oldReview.setRating(review.getRating());
            }
            if(review.getCompanyId() != null){
                oldReview.setCompanyId(review.getCompanyId());
            }

            reviewRepository.save(oldReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElse(null);

        if(review != null){
            reviewRepository.delete(review);
            return true;
        }
        else{
            return false;
        }
    }

}
