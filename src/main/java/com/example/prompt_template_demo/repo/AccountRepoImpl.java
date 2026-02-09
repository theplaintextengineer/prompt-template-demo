package com.example.prompt_template_demo.repo;

import org.springframework.stereotype.Service;

import com.example.prompt_template_demo.model.AccountDetails;

@Service
public class AccountRepoImpl implements AccountRepo {

  private final AccountDetails accountDetails;

  public AccountRepoImpl() {
    this.accountDetails = new AccountDetails(100001468615L, "Akshay Singh", 23455.55);
  }

  @Override
  public AccountDetails getAccountDetails() {
    return this.accountDetails;
  }

}
