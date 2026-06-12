package com.healthcare.HealthcareAI.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "billing")
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceId;
    private String patientName;
    private String doctorName;
    private String billDate;

    private Double consultationFee;
    private Double medicineFee;
    private Double labFee;

    private Double totalAmount;
    private Double gstAmount;
    private Double grandTotal;

    private String paymentStatus;
    private String paymentLinkId;
    private String paymentLinkUrl;
    private String paymentReference;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getInvoiceId() { return invoiceId; }
    public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String getBillDate() { return billDate; }
    public void setBillDate(String billDate) { this.billDate = billDate; }

    public Double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(Double consultationFee) { this.consultationFee = consultationFee; }

    public Double getMedicineFee() { return medicineFee; }
    public void setMedicineFee(Double medicineFee) { this.medicineFee = medicineFee; }

    public Double getLabFee() { return labFee; }
    public void setLabFee(Double labFee) { this.labFee = labFee; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public Double getGstAmount() { return gstAmount; }
    public void setGstAmount(Double gstAmount) { this.gstAmount = gstAmount; }

    public Double getGrandTotal() { return grandTotal; }
    public void setGrandTotal(Double grandTotal) { this.grandTotal = grandTotal; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getPaymentLinkId() { return paymentLinkId; }
    public void setPaymentLinkId(String paymentLinkId) { this.paymentLinkId = paymentLinkId; }

    public String getPaymentLinkUrl() { return paymentLinkUrl; }
    public void setPaymentLinkUrl(String paymentLinkUrl) { this.paymentLinkUrl = paymentLinkUrl; }

    public String getPaymentReference() { 
        return paymentReference;
     }
    public void setPaymentReference(String paymentReference) { 
        this.paymentReference = paymentReference;
     }
}