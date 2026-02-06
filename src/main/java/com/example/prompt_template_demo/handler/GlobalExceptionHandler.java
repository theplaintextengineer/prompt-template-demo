package com.example.prompt_template_demo.handler;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.prompt_template_demo.exception.BankAccountNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception e) {
    return getErrorResponse(e, 500);
  }

  @ExceptionHandler(BankAccountNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleException(BankAccountNotFoundException e) {
    return getErrorResponse(e, 404);
  }

  private ResponseEntity<ErrorResponse> getErrorResponse(Exception e, int statusCode) {
    var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(statusCode), e.getMessage());
    var errorResponse = ErrorResponse.builder(e, problemDetails)
        .build();

    return ResponseEntity
        .status(HttpStatusCode.valueOf(statusCode))
        .body(errorResponse);
  }
}