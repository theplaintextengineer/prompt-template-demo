package com.example.prompt_template_demo.service.bankaccount;

import com.example.prompt_template_demo.model.BankAccount;

public interface BankAccountService {
  BankAccount getBankAccount(Long accountNumber);
}
