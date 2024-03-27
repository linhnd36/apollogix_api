package com.apollogix.managerskill.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BusinessException extends Exception {

    private HttpStatus httpStatus;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
