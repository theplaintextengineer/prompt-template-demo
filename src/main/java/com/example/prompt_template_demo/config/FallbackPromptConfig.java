package com.example.prompt_template_demo.config;

import org.springframework.ai.template.NoOpTemplateRenderer;
import org.springframework.ai.template.TemplateRenderer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(PromptConfig.class)
public class FallbackPromptConfig {
  @Bean
  PromptConfig promptConfig() {
    return new PromptConfig(null, null, null);
  }

  @Bean
  TemplateRenderer templateRenderer() {
    return new NoOpTemplateRenderer();
  }
}
