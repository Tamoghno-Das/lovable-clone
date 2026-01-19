package com.example.service.impl;

import com.example.dto.subscription.CheckoutRequest;
import com.example.dto.subscription.CheckoutResponse;
import com.example.dto.subscription.PortalResponse;
import com.example.dto.subscription.SubscriptionResponse;
import com.example.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getCurrentSubscription(Long userId) {
        return null;
    }

}
