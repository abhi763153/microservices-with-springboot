package com.abhi_app.CompanyMS.CompanyRepository;

import com.abhi_app.CompanyMS.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
