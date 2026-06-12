package com.healthcare.HealthcareAI.controller;

import com.healthcare.HealthcareAI.service.BillingService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RazorpayWebhookController {

    @Autowired
    private BillingService billingService;

    @PostMapping("/payment/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload) {

        try {
            JSONObject json = new JSONObject(payload);

            String event = json.getString("event");

            if ("payment_link.paid".equals(event)) {

                JSONObject paymentLink =
                        json.getJSONObject("payload")
                                .getJSONObject("payment_link")
                                .getJSONObject("entity");

                String invoiceId = paymentLink.getString("reference_id");
                String paymentLinkId = paymentLink.getString("id");

                billingService.markPaidByInvoiceId(invoiceId, paymentLinkId);

                System.out.println("Payment Success for Invoice: " + invoiceId);
            }

            return ResponseEntity.ok("Webhook Received");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Webhook Error");
        }
    }
}