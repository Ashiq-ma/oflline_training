package com.student.ust.Exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String exceptionMessage){
        super(exceptionMessage);
    }

}
