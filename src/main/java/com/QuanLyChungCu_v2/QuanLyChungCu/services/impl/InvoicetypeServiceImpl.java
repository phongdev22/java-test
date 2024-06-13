
package com.QuanLyChungCu_v2.QuanLyChungCu.services.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Invoicetype;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.InvoicetypeRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.InvoicetypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoicetypeServiceImpl implements InvoicetypeService {

    @Autowired
    private InvoicetypeRepository invoicetypeRepo;

    @Override
    public List<Invoicetype> getInvoicetypes() {
        return invoicetypeRepo.getInvoicetypes();
    }

    @Override
    public Invoicetype getInvoicetypeById(int id) {
        return invoicetypeRepo.getInvoicetypeById(id);
    }

}
