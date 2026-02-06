package com.example.prompt_template_demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prompt_template_demo.model.BankAccount;

public interface BankAccountRepo extends JpaRepository<BankAccount, Long> {

}
