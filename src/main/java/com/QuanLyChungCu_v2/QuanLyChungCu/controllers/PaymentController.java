package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    @GetMapping("")
    public String Index(){
        return "page-payment";
    }

    @Autowired
    @GetMapping("/callback")
    public ResponseEntity<Map<String, Object>> Callback(){
        return new ResponseEntity<>(new HashMap<>(){}, HttpStatus.OK);
    }
}
