package com.example.controller;

import com.example.dto.subscription.PlanLimitsResponse;
import com.example.dto.subscription.UsageTodayResponse;
import com.example.service.UsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usage")
public class UsageController {

    private final UsageService usageService;

    @GetMapping("/today")
    public ResponseEntity<UsageTodayResponse> getUsageToday()
    {
        Long userId = 1L;
        return ResponseEntity.ok(usageService.getUsageToday(userId));

    }

    @GetMapping("/limits")

    public ResponseEntity<PlanLimitsResponse> getPlanLimits()
    {
        Long userId = 1L;
        return ResponseEntity.ok(usageService.getSubscriptionLimit(userId));
    }




}
