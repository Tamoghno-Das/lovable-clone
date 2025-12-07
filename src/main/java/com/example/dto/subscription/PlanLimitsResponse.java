package com.example.dto.subscription;

public record PlanLimitsResponse
        (
                String planName,
                Integer maxTokensPerDay,
                Integer maxProjects,
                Boolean unlimitedAI

        ) {
}
