package com.healthcare.HealthcareAI.repository;

import com.healthcare.HealthcareAI.entity.Billing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillingRepository
        extends JpaRepository<Billing, Long> {

    List<Billing> findByPatientNameContainingIgnoreCase(
            String patientName);

    List<Billing> findByDoctorNameContainingIgnoreCase(
            String doctorName);

    Billing findByInvoiceId(
            String invoiceId);

    @Query("""
    SELECT COALESCE(SUM(b.totalAmount), 0)
    FROM Billing b
    WHERE b.paymentStatus = 'Paid'
    """)
    Double getTotalRevenue();
}