package com.abhi_app.CompanyMS.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "ReviewMS", url = "${review-service.url}")
public interface ReviewClient {

    @GetMapping("/review/average-rating")
    public Double getAverageRatingForCompany(@RequestParam("companyId") Long companyId);

}
