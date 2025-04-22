package com.abhi_app.CompanyMS.Messaging;

import com.abhi_app.CompanyMS.CompanyService.CompanyServiceImpl;
import com.abhi_app.CompanyMS.DTO.ReviewMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {

    private static final Logger log = LoggerFactory.getLogger(ReviewMessageConsumer.class);

    @Autowired
    private CompanyServiceImpl companyService;

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage){
        if (reviewMessage == null) {
            log.error("Received null reviewMessage");
        } else {
            log.info("Received reviewMessage: {}", reviewMessage);
        }
        companyService.updateCompanyRating(reviewMessage);
    }
}
