package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Invoice;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.InvoiceService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping()
    public String listInvoice(Model model){
//        List<Invoice> list = invoiceService.getAll();
//        System.out.println("List invoice"+ list.size());
//        model.addAttribute("invoices", list );
        return "list-invoice";
    }
}
