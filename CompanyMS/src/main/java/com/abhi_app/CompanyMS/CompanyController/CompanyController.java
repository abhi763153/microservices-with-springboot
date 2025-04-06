package com.abhi_app.CompanyMS.CompanyController;

import com.abhi_app.CompanyMS.CompanyService.CompanyServiceImpl;
import com.abhi_app.CompanyMS.Entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    CompanyServiceImpl companyService;

    @GetMapping
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @PostMapping("/create")
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Added successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        return company != null ? ResponseEntity.ok(company) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public boolean updateCompany(@PathVariable Long id, @RequestBody Company company){
        return companyService.updateCompany(id, company);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCompany(@PathVariable Long id){
        return companyService.deleteCompany(id);
    }

}
