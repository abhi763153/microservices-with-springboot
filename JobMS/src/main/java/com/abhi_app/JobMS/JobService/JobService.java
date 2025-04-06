package com.abhi_app.JobMS.JobService;

import com.abhi_app.JobMS.DTO.JobDTO;
import com.abhi_app.JobMS.Entities.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {
    List<JobDTO> getAllJobs();

    void creatJob(Job job);

    JobDTO getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updateJob);
}
