package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    @RequestMapping("")
    public String Index(){
        return "page-feedback";
    }
}
