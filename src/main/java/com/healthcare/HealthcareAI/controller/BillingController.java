package com.healthcare.HealthcareAI.controller;

import com.healthcare.HealthcareAI.entity.Billing;
import com.healthcare.HealthcareAI.service.BillingService;
import com.healthcare.HealthcareAI.service.RazorpayPaymentService;
import com.razorpay.PaymentLink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingService service;

    @Autowired
    private RazorpayPaymentService razorpayPaymentService;

    @GetMapping("/")
    public String billing(Model model) {
        model.addAttribute("bills", service.getAllBills());
        return "billing-list";
    }

    @GetMapping("/addBill")
    public String addBill(Model model) {
        model.addAttribute("bill", new Billing());
        return "add-billing";
    }

    @PostMapping("/saveBill")
    public String saveBill(@ModelAttribute Billing bill) {
        service.saveBill(bill);
        return "redirect:/billing/";
    }

    @GetMapping("/edit/{id}")
    public String editBillPage(@PathVariable Long id, Model model) {
        Billing bill = service.getBillById(id);
        model.addAttribute("bill", bill);
        return "edit-billing";
    }

    @PostMapping("/updateBill")
    public String updateBill(@ModelAttribute Billing bill) {
        service.updateBill(bill);
        return "redirect:/billing/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBill(@PathVariable Long id) {
        service.deleteBill(id);
        return "redirect:/billing/";
    }

    @GetMapping("/search")
    public String searchBills(@RequestParam String keyword, Model model) {
        model.addAttribute("bills", service.searchBills(keyword));
        return "billing-list";
    }

    @GetMapping("/generatePaymentLink/{id}")
    public String generatePaymentLink(@PathVariable Long id) throws Exception {

        Billing bill = service.getBillById(id);

        PaymentLink paymentLink =
                razorpayPaymentService.createPaymentLink(bill);

        bill.setPaymentLinkId(String.valueOf(paymentLink.get("id")));
        bill.setPaymentLinkUrl(String.valueOf(paymentLink.get("short_url")));
        bill.setPaymentStatus("Pending");

        service.updateBill(bill);

        return "redirect:/billing/";
    }

    @GetMapping("/paid/{id}")
    public String markPaid(@PathVariable Long id) {
        service.markAsPaid(id);
        return "redirect:/billing/";
    }
    @GetMapping("/pay/{id}")
public String paymentGateway(@PathVariable Long id, Model model){

    Billing bill = service.getBillById(id);

    model.addAttribute("bill", bill);

    return "payment-gateway";
}

@GetMapping("/paymentSuccess/{id}")
public String paymentSuccess(@PathVariable Long id){

    service.markAsPaid(id);

    return "redirect:/billing/invoice/" + id;
}

@GetMapping("/invoice/{id}")
public String invoicePage(@PathVariable Long id, Model model){

    Billing bill = service.getBillById(id);

    model.addAttribute("bill", bill);

    return "invoice";
}
}