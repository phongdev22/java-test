package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Feedback;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("")
    public String Index(){
        return "page-feedback";
    }

    @GetMapping("/list")
    public String showFeedbackList(Model model) {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        model.addAttribute("feedbacks", feedbacks);
        return "feedback-list";
    }

    @GetMapping("/create")
    public String showAddFeedbackForm(Model model) {
        Feedback feedback = new Feedback();
        model.addAttribute("feedback", feedback);
        return "form-feedback";
    }

    @GetMapping("/edit/{id}")
    public String showEditFeedbackForm(@PathVariable("id") Integer id, Model model) {
        Optional<Feedback> feedback = feedbackService.getFeedbackById(id);
        if (feedback.isPresent()) {
            model.addAttribute("feedback", feedback.get());
        } else {
            model.addAttribute("error", "Feedback not found");
        }
        return "form-feedback";
    }

    @GetMapping("/delete/{id}")
    public String deleteFeedback(@PathVariable("id") Integer id) {
        feedbackService.deleteFeedback(id);
        return "redirect:/feedback/list";
    }
}
