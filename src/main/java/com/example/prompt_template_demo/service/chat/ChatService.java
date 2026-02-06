package com.example.prompt_template_demo.service.chat;

import reactor.core.publisher.Flux;

public interface ChatService {
  Flux<String> talkToLlm(String message);
}
