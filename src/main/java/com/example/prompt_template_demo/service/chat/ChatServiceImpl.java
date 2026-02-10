package com.example.prompt_template_demo.service.chat;

import java.util.HashMap;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.template.TemplateRenderer;
import org.springframework.stereotype.Service;

import com.example.prompt_template_demo.config.PromptConfig;
import com.example.prompt_template_demo.repo.AccountRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {
    private final ChatClient chatClient;
    private final AccountRepo accountRepo;
    private final PromptConfig promptConfig;
    private final TemplateRenderer customeTemplateRenderer;

    @Override
    public Flux<String> talkToLlm(String message) {
        // System Message

        var sysPt = SystemPromptTemplate.builder()
                .resource(promptConfig.getSystemTemplate())
                .renderer(customeTemplateRenderer)
                .build();

        // User Message
        var params = new HashMap<String, Object>();
        params.put("query", message);
        params.put("accountDetails", accountRepo.getAccountDetails());

        var userPt = PromptTemplate.builder()
                .resource(promptConfig.getUserTemplate())
                .renderer(customeTemplateRenderer)
                .variables(params)
                .build();

        // For System -> System Message
        var systemMessage = SystemMessage.builder()
                .text(sysPt.render())
                .build();

        // For User -> User Message
        var userMessage = UserMessage.builder()
                .text(userPt.render())
                .build();

        log.info("System Message: {}", systemMessage);
        log.info("User Message: {}", userMessage);

        Prompt prompt = Prompt.builder()
                .messages(systemMessage, userMessage)
                .build();

        return chatClient
                .prompt(prompt)
                .stream()
                .content();
    }

}
