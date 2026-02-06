package com.example.prompt_template_demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prompt_template_demo.model.BankAccount;
import com.example.prompt_template_demo.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

  List<Transaction> findAllByBankAccount(BankAccount bankAccount);

}
