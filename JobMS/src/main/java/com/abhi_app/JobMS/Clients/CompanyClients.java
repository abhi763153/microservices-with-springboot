package com.abhi_app.JobMS.Clients;

import com.abhi_app.JobMS.DTO.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANYMS", url = "${company-service.url}")
public interface CompanyClients {

    @GetMapping("/company/{id}")
    Company getCompany(@PathVariable Long id);
}
