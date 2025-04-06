package com.abhi_app.JobMS.JobController;

import com.abhi_app.JobMS.DTO.JobDTO;
import com.abhi_app.JobMS.Entities.Job;
import com.abhi_app.JobMS.JobService.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    JobServiceImpl jobService;


    @GetMapping
    public List<JobDTO> getAllJobs(){
        return jobService.getAllJobs();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.creatJob(job);

        return new ResponseEntity<>("Job added", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id){
        JobDTO jobWithCompany = jobService.getJobById(id);
        return jobWithCompany != null ? ResponseEntity.ok(jobWithCompany) : ResponseEntity.notFound().build();
     }

    @PutMapping("/update/{id}")
    public boolean updateJob(@PathVariable Long id, @RequestBody Job job){
        return jobService.updateJob(id, job);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteJob(@PathVariable Long id){
        return jobService.deleteJobById(id);
    }

}
