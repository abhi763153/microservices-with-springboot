package com.abhi_app.ReviewMS.ReviewController;

import com.abhi_app.ReviewMS.Entity.Review;
import com.abhi_app.ReviewMS.Exceptions.ErrorResponse;
import com.abhi_app.ReviewMS.Exceptions.ReviewNotFoundException;
import com.abhi_app.ReviewMS.Messaging.ReviewMessageProducer;
import com.abhi_app.ReviewMS.ReviewService.ReviewServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("review")
public class ReviewController {

    @Autowired
    private ReviewServiceImple reviewServiceImple;

    @Autowired
    private ReviewMessageProducer reviewMessageProducer;

    @GetMapping
    public List<Review> getReviewsByCompany(@RequestParam("companyId") Long companyId){
        return reviewServiceImple.getAllReviews(companyId);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getReviewById(@PathVariable Long reviewId){

        Review review = reviewServiceImple.getReviewById(reviewId);
        return review != null
                ? new ResponseEntity<>(review, HttpStatus.OK)
                : new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);

    }

    @PostMapping("create")
    public ResponseEntity<?> createReview(@RequestBody Review review){

        if(reviewServiceImple.createReviews(review)){
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("Review created successfully!", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Review failed to create!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable  Long reviewId, @RequestBody  Review review){

        return reviewServiceImple.updateReview(reviewId, review)
                ? new ResponseEntity<>("Review updated successfully!", HttpStatus.OK)
                : new ResponseEntity<>("Updation failed!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){

        return reviewServiceImple.deleteReview(reviewId)
                ? new ResponseEntity<>("Review deleted successfully!", HttpStatus.OK)
                : new ResponseEntity<>("Deletion failed!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/average-rating")
    public double getAverageReview(@RequestParam Long companyId){
        List<Review> reviews = reviewServiceImple.getAllReviews(companyId);

        return reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }




}
