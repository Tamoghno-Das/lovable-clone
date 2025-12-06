package com.example.service;

import com.example.dto.subscription.PlanLimitsResponse;
import com.example.dto.subscription.UsageTodayResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public interface UsageService {

    UsageTodayResponse getUsageToday(Long userId);

    PlanLimitsResponse getSubscriptionLimit(Long userId);
}
