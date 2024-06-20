package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Invoice;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.InvoiceService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.VNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private VNPayService vnPayService;

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/vnpay")
    public String Callback(@RequestParam Map<String, String> params){
        if (vnPayService.VerifySignature(params)) {
            String orderId = params.get("vnp_TxnRef");
            String responseCode = params.get("vnp_ResponseCode");
            System.out.println("Da goi callback");

            String message = "00".equals(responseCode) ? "Success" : "Fail";
            vnPayService.UpdatePaymentStatus(orderId, message);
        }
        return "redirect:/invoices";
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Object> GetStatusInvoice(@PathVariable("id")Integer id){
        return new ResponseEntity<>(new Object(), HttpStatus.OK);
    }

    @GetMapping("/create/{invoiceId}")
    public ResponseEntity<Object> createPaymentUrl(@PathVariable("invoiceId")Integer id){
        HashMap<String, Object> res = new HashMap<>();
        try{
            Invoice invoice = invoiceService.GetById(id);
            int amount = invoice.getAmount().intValue();
            String orderInfo = "Pay for invoice code " + invoice.getId();
            String paymentUrl = vnPayService.CreateUrlPayment(amount, orderInfo, invoice.paymentCode, "http://localhost:8080");

            res.put("code", 0);
            res.put("message", "Success!");
            res.put("paymentUrl", paymentUrl);
            res.put("data", invoice);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch(Exception ex){
            res.put("code", 1);
            res.put("message", "Fail!");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
}
