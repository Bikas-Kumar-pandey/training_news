package com.news.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) throws Exception {
        var errorResponse = com.news.exception.ErrorResponse.builder().errorMessage(ex.getMessage()).build();
        return  new ResponseEntity<com.news.exception.ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);

    }

  protected ResponseEntity<Object> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException type, HttpHeaders headers, WebRequest request, HttpStatus status) throws Exception{
        return new ResponseEntity<Object>("Please check your Http method",HttpStatus.NOT_FOUND);
  }

}
