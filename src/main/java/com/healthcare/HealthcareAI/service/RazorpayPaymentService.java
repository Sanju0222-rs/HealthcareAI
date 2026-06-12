package com.healthcare.HealthcareAI.service;

import com.healthcare.HealthcareAI.entity.Billing;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RazorpayPaymentService {

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    public PaymentLink createPaymentLink(Billing bill) throws Exception {

        RazorpayClient razorpay = new RazorpayClient(keyId, keySecret);

        double amount = 0;

        if (bill.getGrandTotal() != null) {
            amount = bill.getGrandTotal();
        } else if (bill.getTotalAmount() != null) {
            amount = bill.getTotalAmount();
        }

        JSONObject request = new JSONObject();

        request.put("amount", Math.round(amount * 100));
        request.put("currency", "INR");
        request.put("accept_partial", false);
        request.put("description", "HealthcareAI Bill " + bill.getInvoiceId());
        request.put("reference_id", bill.getInvoiceId());

        JSONObject customer = new JSONObject();
        customer.put("name", bill.getPatientName());
        customer.put("contact", "9999999999");
        customer.put("email", "patient@example.com");

        request.put("customer", customer);

        JSONObject notify = new JSONObject();
        notify.put("sms", false);
        notify.put("email", false);
        request.put("notify", notify);

        JSONObject notes = new JSONObject();
        notes.put("invoiceId", bill.getInvoiceId());
        notes.put("patientName", bill.getPatientName());
        notes.put("doctorName", bill.getDoctorName());

        request.put("notes", notes);

        return razorpay.paymentLink.create(request);
    }
}