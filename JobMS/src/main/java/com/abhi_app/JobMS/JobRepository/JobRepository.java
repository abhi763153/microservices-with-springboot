package com.abhi_app.JobMS.JobRepository;

import com.abhi_app.JobMS.Entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository<Job, Long> {
}
