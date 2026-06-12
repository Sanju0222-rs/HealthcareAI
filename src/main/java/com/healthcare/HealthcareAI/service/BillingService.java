package com.healthcare.HealthcareAI.service;

import com.healthcare.HealthcareAI.entity.Billing;
import com.healthcare.HealthcareAI.repository.BillingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingService {

    @Autowired
    private BillingRepository repository;

    public Billing saveBill(Billing bill) {

        double consultation =
                bill.getConsultationFee() == null ? 0 : bill.getConsultationFee();

        double medicine =
                bill.getMedicineFee() == null ? 0 : bill.getMedicineFee();

        double lab =
                bill.getLabFee() == null ? 0 : bill.getLabFee();

        double subtotal =
                consultation + medicine + lab;

        double gst =
                subtotal * 0.18;

        double grandTotal =
                subtotal + gst;

        bill.setTotalAmount(subtotal);
        bill.setGstAmount(gst);
        bill.setGrandTotal(grandTotal);

        if (bill.getPaymentStatus() == null ||
                bill.getPaymentStatus().isEmpty()) {

            bill.setPaymentStatus("Pending");
        }

        return repository.save(bill);
    }

    public Billing updateBill(Billing bill) {

        return saveBill(bill);
    }

    public List<Billing> getAllBills() {

        return repository.findAll();
    }

    public Billing getBillById(Long id) {

        return repository.findById(id).orElse(null);
    }

    public void deleteBill(Long id) {

        repository.deleteById(id);
    }

    public List<Billing> searchBills(String keyword) {

        return repository.findByPatientNameContainingIgnoreCase(keyword);
    }

    public List<Billing> getBillsByPatient(String patientName) {

        return repository.findByPatientNameContainingIgnoreCase(patientName);
    }

    public List<Billing> getBillsByDoctor(String doctorName) {

        return repository.findByDoctorNameContainingIgnoreCase(doctorName);
    }

    public double getDoctorIncome(String doctorName) {

        List<Billing> bills =
                repository.findByDoctorNameContainingIgnoreCase(doctorName);

        double total = 0;

        for (Billing bill : bills) {

            if (bill.getPaymentStatus() != null &&
                    bill.getPaymentStatus().equalsIgnoreCase("Paid")) {

                total += bill.getConsultationFee() == null
                        ? 0
                        : bill.getConsultationFee();
            }
        }

        return total;
    }

    public Billing getBillByInvoiceId(String invoiceId) {

        return repository.findByInvoiceId(invoiceId);
    }

    public void markPaidByInvoiceId(String invoiceId,
                                    String paymentReference) {

        Billing bill =
                repository.findByInvoiceId(invoiceId);

        if (bill != null) {

            bill.setPaymentStatus("Paid");
            bill.setPaymentReference(paymentReference);

            repository.save(bill);
        }
    }

    public void markAsPaid(Long id) {

        Billing bill =
                repository.findById(id).orElse(null);

        if (bill != null) {

            double consultation =
                    bill.getConsultationFee() == null
                            ? 0
                            : bill.getConsultationFee();

            double medicine =
                    bill.getMedicineFee() == null
                            ? 0
                            : bill.getMedicineFee();

            double lab =
                    bill.getLabFee() == null
                            ? 0
                            : bill.getLabFee();

            double subtotal =
                    consultation + medicine + lab;

            double gst =
                    subtotal * 0.18;

            double grandTotal =
                    subtotal + gst;

            bill.setTotalAmount(subtotal);
            bill.setGstAmount(gst);
            bill.setGrandTotal(grandTotal);
            bill.setPaymentStatus("Paid");

            repository.save(bill);
        }
    }
}