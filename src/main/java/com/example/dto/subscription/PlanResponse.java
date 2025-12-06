package com.example.dto.subscription;

public record PlanResponse
        (
                Long id,
                String name,
                Integer maxProjects,
                Integer maxTokensPerDay,
                Boolean unlimitedAI,// Unlimited  access to LLM and it will ignore the maxTokensPerDay if true
                String price
        ) {
}
