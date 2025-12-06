package com.example.service;

import com.example.dto.subscription.CheckoutRequest;
import com.example.dto.subscription.CheckoutResponse;
import com.example.dto.subscription.PortalResponse;
import com.example.dto.subscription.SubscriptionResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public interface SubscriptionService {

    SubscriptionResponse getCurrentSubscription(Long userId);
    CheckoutResponse createCheckSessionUrl(CheckoutRequest request, Long userId);
    PortalResponse openCustomerPortal(Long userId);
}
