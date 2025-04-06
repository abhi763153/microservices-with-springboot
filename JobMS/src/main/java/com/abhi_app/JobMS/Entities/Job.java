package com.abhi_app.JobMS.Entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@Table(name="job_table")
@Entity
@Getter
@Setter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Long companyId;

    public Job() {}

    public Job(Long id, String title, String description, String minSalary, String maxSalary, String location, Long companyId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
        this.companyId = companyId;
    }
}
