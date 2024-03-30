package com.apollogix.managerskill.exception.handler;

import com.apollogix.managerskill.exception.BusinessException;
import com.apollogix.managerskill.exception.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This is a class named CustomExceptionHandler.
 * It handles exceptions and generates appropriate response entities.
 * It has methods to handle BusinessException and BadCredentialsException.
 * The handleException method handles BusinessException and logs a warning message.
 * The handleExceptionBadCredentialsException method handles BadCredentialsException.
 * The buildResponseEntity method builds the response entity using an ErrorResponse object.
 */
@ControllerAdvice
public class CustomExceptionHandler {
    Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    private ResponseEntity<?> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    /**
     * Handles a BusinessException and generates an appropriate response entity.
     *
     * @param e The BusinessException to handle
     * @return The ResponseEntity containing the error response with status code 500
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<?> handleException(BusinessException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (e.getHttpStatus() != null) {
            httpStatus = e.getHttpStatus();
        }
        logger.warn(e.getMessage());
        return buildResponseEntity(new ErrorResponse(httpStatus, e.getMessage()));
    }

    /**
     * Handles a BadCredentialsException and generates an appropriate response entity.
     *
     * @param e The BadCredentialsException to handle
     * @return The ResponseEntity containing the error response with status code 403
     */
    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<?> handleExceptionBadCredentialsException(BadCredentialsException e) {
        return buildResponseEntity(new ErrorResponse(HttpStatus.FORBIDDEN, e.getMessage()));
    }

    /**
     * Handles Another Exception and generates an appropriate response entity.
     *
     * @param e The Exception to handle
     * @return The ResponseEntity containing the error response with status code 500
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return buildResponseEntity(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }

}
