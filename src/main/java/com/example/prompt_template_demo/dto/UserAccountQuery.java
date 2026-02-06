package com.example.prompt_template_demo.dto;

import org.jspecify.annotations.Nullable;

public record UserAccountQuery(Long accountNumber, @Nullable Long userId) {
}