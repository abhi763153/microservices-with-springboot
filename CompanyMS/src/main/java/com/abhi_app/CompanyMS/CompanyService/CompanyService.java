package com.abhi_app.CompanyMS.CompanyService;


import com.abhi_app.CompanyMS.DTO.ReviewMessage;
import com.abhi_app.CompanyMS.Entity.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    void createCompany(Company company);

    Company getCompanyById(Long id);

    boolean updateCompany(Long id, Company company);

    boolean deleteCompany(Long id);

    public void updateCompanyRating(ReviewMessage reviewMessage);
}
