package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Invoice;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.InvoiceRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepo;
    @Autowired
    private PaymentRepository paymentRepo;

    public List<Invoice> GetAllInvoiceByUserId(){
        return new ArrayList<Invoice>();
    }

}
