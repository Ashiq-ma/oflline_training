package com.student.ust.Exception;

/**
 * The type Business exception.
 */
public class BusinessException extends RuntimeException {

    /**
     * Instantiates a new Business exception.
     *
     * @param exceptionMessage the exception message
     */
    public BusinessException(String exceptionMessage){
        super(exceptionMessage);
    }

}
