package com.example.prompt_template_demo.service.bankaccount;

import org.springframework.stereotype.Service;

import com.example.prompt_template_demo.model.BankAccount;
import com.example.prompt_template_demo.repo.BankAccountRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

  private final BankAccountRepo repo;

  @Override
  public BankAccount getBankAccount(Long accountNumber) {
    return repo.findById(accountNumber).orElseThrow();
  }

}
