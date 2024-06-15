package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Feedback;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.FeedbackService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping()
    public String getALlFeedback(Model model) {
//        List<Feedback> list = feedbackService.getAll();
//        model.addAttribute("feedbacks", list);
        return "list-feedback";
    }
}
