package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.dto.RelativeRegistrationDTO;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.RelativeRegistration;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.RelativeRegistrationService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/relative-registration")
public class RelativeRegistrationController {

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private RelativeRegistrationService service;

    @GetMapping()
    public String Index(){
        return "page-register-relative";
    }

    @GetMapping("/list")
    public String listAll(Model model) {
        model.addAttribute("content", service.findAll());
        return "list-register-relative";
    }

    @GetMapping("/create")
    public String showRegistrationForm(Model model) {
        model.addAttribute("users", userEntityService.findAll());
        model.addAttribute("relativeRegistration", new RelativeRegistration());
        return "form-register-relative";
    }

    @GetMapping("/{id}")
    public String Details(@PathVariable("id")Integer id, Model model) {
        model.addAttribute("users", userEntityService.findAll());
        model.addAttribute("relativeRegistration", service.findById(id).get());
        return "form-register-relative";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteRegistration(@PathVariable("id") Integer id) {
        Map<String, Object> response = new HashMap<>();
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            response.put("code", 0);
            response.put("message", "Relative registration deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("code", 1);
            response.put("message", "Relative registration not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> createOrUpdate(@ModelAttribute RelativeRegistrationDTO relativeRegistrationDTO) {
        Map<String, Object> response = new HashMap<>();
        try{
            service.save(relativeRegistrationDTO);
            response.put("code", 0);
            response.put("message", "Relative registration saved successfully");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            response.put("code", 1);
            response.put("message", "Relative registration saved failed");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
