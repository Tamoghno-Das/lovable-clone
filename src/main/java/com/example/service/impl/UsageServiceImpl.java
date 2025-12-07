package com.example.service.impl;

import com.example.dto.subscription.PlanLimitsResponse;
import com.example.dto.subscription.UsageTodayResponse;
import com.example.service.ProjectMemberService;
import com.example.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponse getUsageToday(Long userId) {
        return null;
    }

    @Override
    public PlanLimitsResponse getSubscriptionLimit(Long userId) {
        return null;
    }
}
