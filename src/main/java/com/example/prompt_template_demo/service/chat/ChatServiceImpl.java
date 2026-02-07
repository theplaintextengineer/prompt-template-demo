package com.example.prompt_template_demo.service.chat;

import java.util.HashMap;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
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
    var template = """
        You are Computer Networks subject expert. You will only the sumarize the topic: {topic} in 5 bullet points.

        Rules:
          - Don't answer the topic that is not related to Computer Networks.
          - If the topic is not related to Computer Networks then simply reply user with "Sorry, I can't help with this".
          - Each bullet point must clear and concise.
        """;

    var params = new HashMap<String, Object>();
    params.put("topic", message);

    var pt = PromptTemplate.builder()
        .template(template)
        .variables(params)
        .build();

    log.info("Final Template: {}", pt.render());

    return chatClient
        .prompt(pt.render())
        .stream()
        .content();
  }

}
