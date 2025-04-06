package com.abhi_app.JobMS.mapper;

import com.abhi_app.JobMS.DTO.Company;
import com.abhi_app.JobMS.DTO.JobDTO;
import com.abhi_app.JobMS.DTO.Review;
import com.abhi_app.JobMS.Entities.Job;

import java.util.List;

public class JobMapper {

    public static JobDTO mapToJobWithCompanyDTO(Job job, Company company, List<Review> review){
        JobDTO jobDTO = new JobDTO();

        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setCompany(company);
        jobDTO.setReviews(review);

        return jobDTO;
    }
}
