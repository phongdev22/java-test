package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Invoice;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepo;

    public void Save(Invoice invoice){
        invoiceRepo.save(invoice);
    }

    public Page<Invoice> GetAllInvoiceByUserId(Integer id, Pageable pageable){
        return invoiceRepo.findByRoomId(id, pageable);
    }

    public Page<Invoice> GetAll(Pageable pageable){
        return invoiceRepo.findAll(pageable);
    }

    public Page<Invoice> GetAllByKeyword(Pageable pageable, String keyword){

        return invoiceRepo.findAllByKeyword(keyword, pageable);
    }

    public Invoice GetById(Integer id){
        return invoiceRepo.findById(id).get();
    }

    public void Delete(Integer id){
        invoiceRepo.deleteById(id);
    }
}
