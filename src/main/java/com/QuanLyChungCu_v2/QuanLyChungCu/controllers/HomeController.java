package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import java.util.ArrayList;

@Controller
@RequestMapping
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Home(Model model){
        System.out.print("Test Home");
        return "home";
    }

    @GetMapping(value = "/login")
    public String GetViewLogin(){
        return "form-login";
    }

    @GetMapping(value = "/logout")
    public String SignOut(){
        return "redirect:/login";
    }
}
