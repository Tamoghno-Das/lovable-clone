package com.example.service;

import com.example.dto.subscription.CheckoutRequest;
import com.example.dto.subscription.CheckoutResponse;
import com.example.dto.subscription.PortalResponse;


public interface PaymentProcessor
{
    CheckoutResponse createCheckSessionUrl(CheckoutRequest request);
    PortalResponse openCustomerPortal(Long userId);
}
