package com.abhi_app.CompanyMS.CompanyService;

import com.abhi_app.CompanyMS.CompanyRepository.CompanyRepository;
import com.abhi_app.CompanyMS.Entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompanies() {
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
}
