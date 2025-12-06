package com.example.dto.subscription;

public record UsageTodayResponse
        (
                int tokensUsed,
                int tokenLimits,
                int previewsRunning,
                int previewsLimit
        ) {
}
