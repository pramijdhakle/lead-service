package com.lead.exception;

public enum ErrorCodes {

    LEAD_DATA_NOT_FOUND("E-001", "Lead data is not available for given input"),
    LEAD_DATA_ALREADY_EXISTS("E-002", "Lead data is already available for given Lead Id");

    private String errorCode;
    private String message;

    ErrorCodes(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
