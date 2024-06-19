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
        model.addAttribute("registrations", service.findAll());
        return "list-register-relative";
    }

    @GetMapping("/create")
    public String showRegistrationForm(Model model) {
        model.addAttribute("users",userEntityService.findAll());
        model.addAttribute("relativeRegistration", new RelativeRegistration());
        return "form-register-relative";
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

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> createOrUpdate(@ModelAttribute RelativeRegistrationDTO relativeRegistrationDTO) {
        System.out.println(relativeRegistrationDTO);

        Map<String, Object> response = new HashMap<>();
        try{
            RelativeRegistration relativeRegistration = new RelativeRegistration();

            relativeRegistration.setUserId(relativeRegistrationDTO.getUserId());
            relativeRegistration.setRelativeName(relativeRegistrationDTO.getRelativeName());
            relativeRegistration.setRelativePhone(relativeRegistrationDTO.getRelativePhone());
            relativeRegistration.setRelationship(relativeRegistrationDTO.getRelationship());
            relativeRegistration.setVehicleRegistrationNumber(relativeRegistrationDTO.getVehicleRegistrationNumber());
            relativeRegistration.setRegistrationDate(relativeRegistrationDTO.getRegistrationDate());
            relativeRegistration.setExpiryDate(relativeRegistrationDTO.getExpiryDate());
            relativeRegistration.setCreatedAt(new Date());
            relativeRegistration.setUpdatedAt(new Date());
            service.save(relativeRegistration);

            response.put("code", 0);
            response.put("message", "Relative registration saved successfully");
        }catch (Exception ex){
            response.put("code", 1);
            response.put("message", "Relative registration saved failed");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
