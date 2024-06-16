package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Invoice;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.PaymentRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.InvoiceService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.VNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ResponseEntity<Object> Callback(@RequestParam Map<String, String> params){
        if (vnPayService.VerifySignature(params)) {
            String orderId = params.get("vnp_TxnRef");
            String responseCode = params.get("vnp_ResponseCode");

            String message = "00".equals(responseCode) ? "Success" : "Fail";
            vnPayService.UpdatePaymentStatus(orderId, message);

        }
        return new ResponseEntity<>(new Object(), HttpStatus.OK);
    }

    @GetMapping("/create")
    public ResponseEntity<Object> createPaymentUrl(@RequestParam("orderId")Integer id){
        try{
            Invoice invoice = invoiceService.GetById(id);
            double amount = invoice.getAmount().doubleValue();
            String orderInfo = "Pay for invoice code " + invoice.getId();
            String paymentUrl = vnPayService.CreateUrlPayment(amount, orderInfo, "http://localhost:8080");
            return new ResponseEntity<>(new Object(), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(new Object(), HttpStatus.OK);
        }
    }
}
