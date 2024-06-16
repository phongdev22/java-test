package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.dto.InvoiceDTO;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Invoice;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private UserEntityService userEntityService;

    @GetMapping("")
    public String Index(){
        return "page-invoice";
    }

    @GetMapping("/list")
    public String GetAll(@RequestParam(defaultValue = "1") int currentPage,
                         @RequestParam(defaultValue = "10") int pageSize,
                         Model model){

        List<InvoiceDTO> invoices = new ArrayList<>(Arrays.asList(
                new InvoiceDTO(23, 101, 1, "zJdhuz", "Room charge for May", new BigDecimal("250.75"), "Room Charge", new Date(), false),
                new InvoiceDTO(42, 102, 2, "Uzjvxz", "Electricity bill for May", new BigDecimal("75.50"), "Electricity", new Date(), true),
                new InvoiceDTO(123,103, 3, "Hktygk", "Water bill for May", new BigDecimal("30.25"), "Water", new Date(), false)
        ));

        model.addAttribute("invoices", invoices);
        return "list-invoice";
    }

    @GetMapping("/create")
    public String CreteInvoice(@ModelAttribute Invoice invoice, Model model){
        model.addAttribute("invoice", new Invoice());
        model.addAttribute("users", userEntityService.findAll());
        // add service
        return "form-invoice";
    }
}
