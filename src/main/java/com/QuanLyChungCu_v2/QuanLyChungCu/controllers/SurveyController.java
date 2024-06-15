package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/surveys")
public class SurveyController {

    @GetMapping("")
    public String Index(){
        return "page-survey";
    }
}
