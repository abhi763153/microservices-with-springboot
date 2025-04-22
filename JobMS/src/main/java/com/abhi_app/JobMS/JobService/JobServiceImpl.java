package com.abhi_app.JobMS.JobService;


import com.abhi_app.JobMS.Clients.CompanyClients;
import com.abhi_app.JobMS.Clients.ReviewClient;
import com.abhi_app.JobMS.DTO.Company;
import com.abhi_app.JobMS.DTO.JobDTO;
import com.abhi_app.JobMS.DTO.Review;
import com.abhi_app.JobMS.Entities.Job;
import com.abhi_app.JobMS.JobRepository.JobRepository;
import com.abhi_app.JobMS.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{

    @Autowired
    JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CompanyClients companyClients;

    @Autowired
    ReviewClient reviewClient;

    @Override
    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOs = new ArrayList<>();

        for(Job job: jobs){
            jobDTOs.add(convertToDTO(job));
        }
        return jobDTOs;
    }

     public List<String> companyBreakerFallback(Exception e){
        return List.of("Company service is temporarily unavailable!");
    }

    public JobDTO convertToDTO(Job job){
        Company company = companyClients.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job, company, reviews);
        return jobDTO;
    }

    @Override
    public void creatJob(Job job) {
        if(job !=null){
            jobRepository.save(job);
        }
    }

    @Override
    public JobDTO getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDTO(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        if(jobRepository.existsById(id)){
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateJob(Long id, Job updateJob) {
        Optional<Job> oldJobOptional = jobRepository.findById(id);

        if(oldJobOptional.isPresent()){
            Job oldJob = oldJobOptional.get();

            if (updateJob.getTitle() != null) {
                oldJob.setTitle(updateJob.getTitle());
            }
            if (updateJob.getDescription() != null) {
                oldJob.setDescription(updateJob.getDescription());
            }
            if (updateJob.getMinSalary() != null) {
                oldJob.setMinSalary(updateJob.getMinSalary());
            }
            if (updateJob.getMaxSalary() != null) {
                oldJob.setMaxSalary(updateJob.getMaxSalary());
            }
            if (updateJob.getLocation() != null) {
                oldJob.setLocation(updateJob.getLocation());
            }

            jobRepository.save(oldJob); // Save the updated job
            return true;
        }

        return false;
    }
}
