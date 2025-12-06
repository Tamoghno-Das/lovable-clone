package com.example.dto.subscription;

public record PlanLimitsResponse
        (
                String planName,
                int maxTokensPerDay,
                int maxProjects,
                Boolean unlimitedAI

        ) {
}
