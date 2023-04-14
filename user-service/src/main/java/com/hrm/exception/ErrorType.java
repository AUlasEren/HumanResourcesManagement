package com.hrm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    EMAIL_DUPLICATE(4001,"E-mail is already exist",HttpStatus.BAD_REQUEST),

    PASSWORD_UNMATCH(4002,"Passwords are not matched",HttpStatus.BAD_REQUEST),
    IDENTIFICATIONNUMBER_DUPLICATE(4003,"IdentificationNumber is already exist",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(5001,"Token not created",HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR(5100,"Sunucu Hatası",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4100,"Parametre Hatası",HttpStatus.BAD_REQUEST),





    ;


    private int code;
    private String message;
     HttpStatus httpStatus;
}
