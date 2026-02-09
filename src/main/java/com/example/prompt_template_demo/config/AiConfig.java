package com.example.prompt_template_demo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {
  @Bean
  ChatClient chatClient(ChatClient.Builder builder) {
    var chatOptions = ChatOptions.builder()
        .model("llama3.2:latest")
        .temperature(0.1)
        .build();

    return builder
        .defaultOptions(chatOptions)
        .build();
  }
}
