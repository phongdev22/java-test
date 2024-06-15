package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    @GetMapping("")
    public String Index(){
        return "page-invoice";
    }

    @GetMapping("/list")
    public String GetAll(@RequestParam(defaultValue = "1") int currentPage,
                         @RequestParam(defaultValue = "10") int pageSize,
                         Model model){
        return "list-bill";
    }
}
