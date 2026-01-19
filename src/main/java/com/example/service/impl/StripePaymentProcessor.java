package com.example.service.impl;

import com.example.dto.subscription.CheckoutRequest;
import com.example.dto.subscription.CheckoutResponse;
import com.example.dto.subscription.PortalResponse;
import com.example.service.PaymentProcessor;
import org.springframework.stereotype.Service;


@Service
public class StripePaymentProcessor implements PaymentProcessor {
    @Override
    public CheckoutResponse createCheckSessionUrl(CheckoutRequest request) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
