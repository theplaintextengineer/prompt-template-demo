package com.example.prompt_template_demo.config;

import org.springframework.ai.template.TemplateRenderer;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import lombok.Getter;

@Configuration
@Getter
@ConditionalOnProperty(name = "app.ai.st-prompt.enabled", havingValue = "true")
public class PromptConfig {
    private final Resource templateFile;

    public PromptConfig(
            @Value("classpath:prompts/subject_expert.st") final Resource templateFile) {
        this.templateFile = templateFile;
    }

    @Bean
    TemplateRenderer templateRenderer() {
        return StTemplateRenderer.builder()
                .startDelimiterToken('<')
                .endDelimiterToken('>')
                .build();
    }
}
