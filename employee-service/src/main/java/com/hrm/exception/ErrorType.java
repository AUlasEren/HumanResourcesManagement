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
    ID_NOT_FOUND(4003,"Id not found",HttpStatus.BAD_REQUEST),
    IDENTIFICATIONNUMBER_DUPLICATE(4004,"IdentificationNumber is already exist",HttpStatus.BAD_REQUEST),
    USER_NOT_CREATED(4005,"User not created",HttpStatus.BAD_REQUEST ),
    VOCATION_NOT_CREATED(3001,"Vocation not created",HttpStatus.BAD_REQUEST ),
    VOCATION_NOT_VALID(3002,"Vocation not valid",HttpStatus.BAD_REQUEST ),
    VOCATION_NOT_REQUESTED(3003,"You don't have any vocation request",HttpStatus.BAD_REQUEST ),
    ADVANCE_NOT_VALID(7001,"Advance not valid",HttpStatus.BAD_REQUEST ),
    AMOUNT_NOT_VALIABLE(7002,"Amount not valiable",HttpStatus.BAD_REQUEST ),
    AMOUND_NOT_NULL(6001,"Amount not null",HttpStatus.BAD_REQUEST ),
    EXPENSE_NOT_VALID(6002,"Expense not valid",HttpStatus.BAD_REQUEST ),
    INVALID_TOKEN(5001,"Token not created",HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR(5100,"Eternal Error",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4100,"Parameter Error",HttpStatus.BAD_REQUEST),


  ;
    private int code;
    private String message;
     HttpStatus httpStatus;
}
