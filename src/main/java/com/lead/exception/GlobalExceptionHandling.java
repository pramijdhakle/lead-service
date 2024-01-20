package com.lead.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(LeadNotFoundException.class)
    public ResponseEntity<ErrorCodeResponse> handleLeadNotFoundException(LeadNotFoundException ex) {
      ErrorCodeResponse errorCodeResponse;
      StackTraceElement traceElement= ex.getStackTrace().length > 0 ? ex.getStackTrace()[0] : null;
      if (ex.getErrorCodes() != null){
          errorCodeResponse= new ErrorCodeResponse(ex.getErrorCodes().getErrorCode(),
                  ex.getErrorCodes().getMessage(), ex.getMessage()+" "+(traceElement != null ? traceElement.toString() : ""));
      } else if (null != ex.getErrorCodeResponse()) {
          return ResponseEntity.status(ex.getStatus()).body(ex.getErrorCodeResponse());
      } else if (ex.getStatus() != null && ex.getMessage() != null) {
          errorCodeResponse= new ErrorCodeResponse(String.valueOf(HttpStatus.NOT_FOUND.value()),
                  ex.getMessage(), traceElement != null ? traceElement.toString() : "");
      }else {
          errorCodeResponse= new ErrorCodeResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                  ex.getMessage(), traceElement != null ? traceElement.toString() : "");
      }
      return ResponseEntity.status(ex.getStatus()).body(errorCodeResponse);
    }

    @ExceptionHandler(LeadAlreadyExistException.class)
    public ResponseEntity<ErrorCodeResponse> handleLeadAlreadyExistException(LeadAlreadyExistException ex) {
        ErrorCodeResponse errorCodeResponse;
        StackTraceElement traceElement= ex.getStackTrace().length > 0 ? ex.getStackTrace()[0] : null;
        if (ex.getErrorCodes() != null){
            errorCodeResponse= new ErrorCodeResponse(ex.getErrorCodes().getErrorCode(),
                    ex.getErrorCodes().getMessage(), ex.getMessage()+" "+(traceElement != null ? traceElement.toString() : ""));
        } else if (null != ex.getErrorCodeResponse()) {
            return ResponseEntity.status(ex.getStatus()).body(ex.getErrorCodeResponse());
        } else if (ex.getStatus() != null && ex.getMessage() != null) {
            errorCodeResponse= new ErrorCodeResponse(String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ex.getMessage(), traceElement != null ? traceElement.toString() : "");
        }else {
            errorCodeResponse= new ErrorCodeResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    ex.getMessage(), traceElement != null ? traceElement.toString() : "");
        }
        return ResponseEntity.status(ex.getStatus()).body(errorCodeResponse);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }
}
