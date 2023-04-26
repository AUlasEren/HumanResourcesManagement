package com.hrm.exception;

import lombok.Getter;

@Getter
public class CompanyManagerServiceException extends RuntimeException{
    private final ErrorType errorType;

    public CompanyManagerServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public CompanyManagerServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }



}
