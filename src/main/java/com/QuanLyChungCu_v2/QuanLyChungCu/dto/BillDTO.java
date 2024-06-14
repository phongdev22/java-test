package com.QuanLyChungCu_v2.QuanLyChungCu.dto;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Invoice;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Payment;

import java.util.Date;

public class BillDTO {
    public Integer InvoiceId;
    public Integer UserId;
    public String BankCode;

    public String Amount;
    public boolean BillStatus;
    public Date InvoiceCreatedDate;
    public String Description;
    public String transactionNo;

    public Date PaymentCreateDate;
    public String PaymentMethod;
}
