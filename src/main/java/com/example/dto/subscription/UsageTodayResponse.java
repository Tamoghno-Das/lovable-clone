package com.example.dto.subscription;

public record UsageTodayResponse
        (
                Integer tokenLimits,
                Integer tokensUsed,
                Integer previewsRunning,
                Integer previewsLimit
        ) {
}
