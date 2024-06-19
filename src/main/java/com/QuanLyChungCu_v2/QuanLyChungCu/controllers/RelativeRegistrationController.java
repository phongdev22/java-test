package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.RelativeRegistration;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.RelativeRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/relative-registration")
public class RelativeRegistrationController {

    @Autowired
    private RelativeRegistrationService service;

    @GetMapping()
    public String Index(){
        return "page-register-relative";
    }

    @GetMapping("/list")
    public String listAll(Model model) {
        model.addAttribute("registrations", service.findAll());
        return "list-register-relative";
    }

    @GetMapping("/new")
    public String showRegistrationForm(Model model) {
        model.addAttribute("relativeRegistration", new RelativeRegistration());
        return "form-register-relative";
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrUpdateRegistration(@ModelAttribute RelativeRegistration relativeRegistration) {
        Map<String, Object> response = new HashMap<>();
        try{
            service.save(relativeRegistration);
            response.put("code", 0);
            response.put("message", "Relative registration saved successfully");
        }catch (Exception ex){
            response.put("code", 1);
            response.put("message", "Relative registration saved faild");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Optional<RelativeRegistration> registration = service.findById(id);
        if (registration.isPresent()) {
            model.addAttribute("relativeRegistration", registration.get());
            return "form-register-relative";
        } else {
            return "redirect:/relative-registration";
        }
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

}
