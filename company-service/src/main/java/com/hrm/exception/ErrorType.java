package com.hrm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    COMPANY_NOT_CREATED(4001,"Company is not created",HttpStatus.BAD_REQUEST),

    COMPANY_ALREADY_EXIST(4002,"Company is already exist",HttpStatus.BAD_REQUEST),
    PASSWORD_UNMATCH(4003,"Passwords are not matched",HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(4004,"Login error",HttpStatus.BAD_REQUEST),
    EMAIL_DUPLICATE(4005,"E-mail is already exist",HttpStatus.BAD_REQUEST),
    COMPANY_NOT_FOUND(4006,"Company is not found",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(5001,"Token not created",HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR(5100,"Eternal Error",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4100,"Parameter Error",HttpStatus.BAD_REQUEST),

    ;


    private int code;
    private String message;
     HttpStatus httpStatus;
}
