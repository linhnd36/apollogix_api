package com.apollogix.managerskill.exception.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class ErrorResponse {

    // property holds the operation call status, which will be anything from 4xx to signal client errors or 5xx to signal server errors
    private HttpStatus httpStatus;

    // Http code
    private Integer httpStatusCode;

    // property holds the date-time instance when the error happened
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    // property holds a user-friendly message about the error.
    private String message;

    // property holds an array of errors when there are multiple errors in a single call
    private List<ValidationErrorResponse> validationErrorResponses;

    private ErrorResponse() {
        timestamp = LocalDateTime.now();
    }

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this();
        this.httpStatus = httpStatus;
        this.httpStatusCode = httpStatus.value();
        this.message = message;
    }
}
