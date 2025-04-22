package com.abhi_app.CompanyMS.CompanyService;

import com.abhi_app.CompanyMS.Clients.ReviewClient;
import com.abhi_app.CompanyMS.CompanyRepository.CompanyRepository;
import com.abhi_app.CompanyMS.DTO.ReviewMessage;
import com.abhi_app.CompanyMS.Entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ReviewClient reviewClient;


    @Override
    public List<Company> getAllCompanies() {

        List<Company> companies = companyRepository.findAll();

        for (Company company: companies){
            Long companyId = company.getId();
        }

        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        if(company!=null){
            companyRepository.save(company);
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.orElse(null);
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        Optional<Company> oldCompanyOptional = companyRepository.findById(id);

        if(oldCompanyOptional.isPresent()){
            Company oldCompany = oldCompanyOptional.get();

            if(company.getName() != null){
                oldCompany.setName(company.getName());
            }
            if(company.getDescription() != null){
                oldCompany.setDescription(company.getDescription());
            }

            companyRepository.save(oldCompany);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteCompany(Long id) {
        try{
            companyRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        Company company = companyRepository.findById(reviewMessage.getCompanyId()).orElse(null);

        if(company != null){
            double avgRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());

            company.setRating(avgRating);

            companyRepository.save(company);
        }

    }
}
