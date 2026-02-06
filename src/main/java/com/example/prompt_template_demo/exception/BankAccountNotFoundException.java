package com.example.prompt_template_demo.exception;

public class BankAccountNotFoundException extends RuntimeException {

  public BankAccountNotFoundException(Long accountNumber) {
    super("Unable to find bank accounrt : %ld".formatted(accountNumber));
  }

}
