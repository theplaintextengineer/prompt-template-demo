package com.example.prompt_template_demo.service.chat;

import java.util.HashMap;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.template.TemplateRenderer;
import org.springframework.stereotype.Service;

import com.example.prompt_template_demo.config.PromptConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {
  private final ChatClient chatClient;
  private final PromptConfig promptConfig;
  private final String subject = "Computer Networks";
  private final TemplateRenderer renderer;

  @Override
  public Flux<String> talkToLlm(String topic) {
    var params = new HashMap<String, Object>();
    params.put("topic", topic);
    params.put("subject", subject);

    var promptTemplate = new SystemPromptTemplate(promptConfig.getTemplateFile());

    var pt = PromptTemplate.builder()
        .renderer(renderer)
        .template(promptTemplate.getTemplate())
        .build();

    log.info("Prompt: {}", pt.render(params));

    return chatClient
        .prompt(pt.render(params))
        .stream()
        .content();
  }

}
