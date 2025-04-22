package com.abhi_app.CompanyMS.DTO;

import lombok.Data;

@Data
public class ReviewMessage {

    private Long id;
    private String title;
    private String description;
    private Double rating;
    private Long companyId;

}
