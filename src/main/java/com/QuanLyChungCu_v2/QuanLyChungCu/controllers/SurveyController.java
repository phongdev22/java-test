package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Room;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Survey;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @RequestMapping()
    public String Index(){
        return "page-survey";
    }

    @GetMapping("/list")
    public String GetAll(@RequestParam(defaultValue = "1") int currentPage,
                        @RequestParam(defaultValue = "7") int pageSize,
                        @RequestParam(defaultValue = "")String keyword,
                        Model model){
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Survey> surveys;
        if (keyword == null || keyword.isEmpty()) {
            surveys = surveyService.GetAll(pageable);
        } else {
            surveys = surveyService.SearchByKeyword(pageable, keyword);
        }

        model.addAttribute("surveys", surveys.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", surveys.getTotalPages());
        return "list-survey";
    }
}
