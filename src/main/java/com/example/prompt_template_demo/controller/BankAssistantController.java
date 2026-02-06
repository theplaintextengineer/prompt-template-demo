package com.example.prompt_template_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.prompt_template_demo.service.chat.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BankAssistantController {
  private final ChatService chatService;

  @GetMapping("/assistant")
  public String getMethodName(@RequestParam String query) {
    return chatService.talkToBankAssistant(query);
  }

}
