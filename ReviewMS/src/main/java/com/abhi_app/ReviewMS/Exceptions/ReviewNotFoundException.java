package com.abhi_app.ReviewMS.Exceptions;

public class ReviewNotFoundException extends  RuntimeException{
    public ReviewNotFoundException(String message) {
        super(message);
    }
}
