package com.lead.exception;

import org.springframework.http.HttpStatus;

public class LeadAlreadyExistException extends RuntimeException{

    private HttpStatus status;
    private ErrorCodes errorCodes;
    private String message;
    private ErrorCodeResponse errorCodeResponse;

    public LeadAlreadyExistException(ErrorCodes errorCodes, HttpStatus httpStatus) {
        this.errorCodes= errorCodes;
        this.status=httpStatus;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(ErrorCodes errorCodes) {
        this.errorCodes = errorCodes;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorCodeResponse getErrorCodeResponse() {
        return errorCodeResponse;
    }

    public void setErrorCodeResponse(ErrorCodeResponse errorCodeResponse) {
        this.errorCodeResponse = errorCodeResponse;
    }

    public LeadAlreadyExistException(String message, HttpStatus status, ErrorCodes errorCodes, String message1, ErrorCodeResponse errorCodeResponse) {
        super(message);
        this.status = status;
        this.errorCodes = errorCodes;
        this.message = message1;
        this.errorCodeResponse = errorCodeResponse;
    }

    public LeadAlreadyExistException(HttpStatus status, ErrorCodes errorCodes) {
        this.status = status;
        this.errorCodes = errorCodes;
    }
}
