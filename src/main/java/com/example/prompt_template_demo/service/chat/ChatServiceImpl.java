package com.example.prompt_template_demo.service.chat;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Supplier;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.template.TemplateRenderer;
import org.springframework.stereotype.Service;

import com.example.prompt_template_demo.config.PromptConfig;
import com.example.prompt_template_demo.dto.UserAccountQuery;
import com.example.prompt_template_demo.exception.BankAccountNotFoundException;
import com.example.prompt_template_demo.repo.BankAccountRepo;
import com.example.prompt_template_demo.repo.TransactionRepo;

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
  private final BankAccountRepo bankAccountRepo;
  private final TransactionRepo transactionRepo;

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

  @Override
  public String talkToBankAssistant(String query) {
    var accountQuery = extractAccountInfo(query);
    log.info("Extracted account query: {}", accountQuery);

    var accountNumber = Objects.requireNonNull(accountQuery.accountNumber());
    var bankAccount = bankAccountRepo.findById(accountNumber)
        .orElseThrow(handleBankAccNotFoundException(accountQuery));

    log.info("Fetched bank account: id={}, user={}, balance={}",
        bankAccount.getId(),
        bankAccount.getUser().getName(),
        bankAccount.getBalance());

    var params = new HashMap<String, Object>();
    params.put("name", bankAccount.getUser().getName());
    params.put("account_number", bankAccount.getId());
    params.put("balance", bankAccount.getBalance());

    var transactions = transactionRepo.findAllByBankAccount(bankAccount);

    params.put("transactions", transactions);
    log.debug("Params prepared for prompt: {}", params);

    params.put("no_of_transactions", Math.min(3, transactions.size()));

    var promptTemplate = new SystemPromptTemplate(promptConfig.getAccInfoFormatterFile());
    log.info("Loaded prompt template from file: {}", promptConfig.getAccInfoFormatterFile());

    var pt = PromptTemplate.builder()
        .renderer(renderer)
        .template(promptTemplate.getTemplate())
        .build();
    log.info("PromptTemplate built successfully");

    String renderedPrompt = pt.render(params);
    log.info("Rendered prompt: {}", renderedPrompt);

    String response = chatClient
        .prompt(renderedPrompt)
        .call()
        .content();
    log.info("ChatClient response received: {}", response);

    return response;
  }

  private Supplier<BankAccountNotFoundException> handleBankAccNotFoundException(UserAccountQuery accountQuery) {
    return () -> {
      log.error("Bank account not found for account number: {}", accountQuery.accountNumber());
      return new BankAccountNotFoundException(accountQuery.accountNumber());
    };
  }

  @Override
  public UserAccountQuery extractAccountInfo(String query) {
    var params = new HashMap<String, Object>();
    params.put("query", query);
    log.debug("Params prepared for prompt: {}", params);

    var promptTemplate = new SystemPromptTemplate(promptConfig.getAccInfoExtractorFile());
    log.info("Loaded prompt template from file: {}", promptConfig.getAccInfoExtractorFile());

    var pt = PromptTemplate.builder()
        .renderer(renderer)
        .template(promptTemplate.getTemplate())
        .build();
    log.info("PromptTemplate built successfully");

    String renderedPrompt = pt.render(params);
    log.info("Rendered prompt: {}", renderedPrompt);

    UserAccountQuery accountQuery = chatClient
        .prompt(renderedPrompt)
        .call()
        .entity(UserAccountQuery.class);

    log.info("ChatClient returned UserAccountQuery: {}", accountQuery);

    return accountQuery;
  }

}
