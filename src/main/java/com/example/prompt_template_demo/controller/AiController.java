package com.example.prompt_template_demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.prompt_template_demo.service.chat.ChatService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class AiController {
  private final ChatService chatService;

  @GetMapping("/")
  public ResponseEntity<Flux<String>> ask(@RequestParam String message) {
    var chatResponse = chatService.talkToLlm(message);

    return ResponseEntity.ok(chatResponse);
  }
}
