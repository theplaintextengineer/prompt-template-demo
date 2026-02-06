package com.example.prompt_template_demo.service.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {
  private final ChatClient chatClient;

  @Override
  public Flux<String> talkToLlm(String message) {
    return chatClient
        .prompt(message)
        .stream()
        .content();
  }

}
