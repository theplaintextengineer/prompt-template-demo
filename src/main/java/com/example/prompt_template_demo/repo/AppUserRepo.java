package com.example.prompt_template_demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prompt_template_demo.model.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

}
