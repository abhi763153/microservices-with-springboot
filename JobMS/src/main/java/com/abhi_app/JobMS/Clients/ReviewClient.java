package com.abhi_app.JobMS.Clients;

import com.abhi_app.JobMS.DTO.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEWMS")
public interface ReviewClient {

    @GetMapping("/review")
    List<Review> getReviews(@RequestParam("companyId") Long id);
}
