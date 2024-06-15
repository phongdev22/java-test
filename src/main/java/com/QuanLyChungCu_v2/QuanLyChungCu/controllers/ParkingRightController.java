package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.services.ParkingRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/parkings")
public class ParkingRightController {

    @Autowired
    private ParkingRightService parkingRightService;

    @GetMapping()
    public String getALlFeedback(Model model) {
//        List<Feedback> list = feedbackService.getAll();
//        model.addAttribute("feedbacks", list);
        return "list-parking";
    }
}
