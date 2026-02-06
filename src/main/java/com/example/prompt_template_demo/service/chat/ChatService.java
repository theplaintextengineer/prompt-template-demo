package com.example.prompt_template_demo.service.chat;

import com.example.prompt_template_demo.dto.UserAccountQuery;

import reactor.core.publisher.Flux;

public interface ChatService {
  Flux<String> talkToLlm(String message);

  String talkToBankAssistant(String query);

  UserAccountQuery extractAccountInfo(String query);
}
