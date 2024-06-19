package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.dto.SurveyDTO;
import com.QuanLyChungCu_v2.QuanLyChungCu.dto.SurveyQuestionDTO;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Room;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Survey;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.SurveyQuestion;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping("")
    public String Index(){
        return "page-survey";
    }

    @PostMapping
    public ResponseEntity<Object> SaveChanges(@RequestBody SurveyDTO surveyDTO){
        HashMap<String, Object> response = new HashMap<>();
        try{
            System.out.println(surveyDTO.getTitle());
            System.out.println(surveyDTO.getDescription());
            System.out.println(surveyDTO.getEndDate());
            System.out.println(surveyDTO.getStartDate());

            surveyService.Save(surveyDTO);
            response.put("code", 0);
            response.put("message", "Create success!");
        }catch (Exception ex){
            response.put("code", 1);
            response.put("message",  ex.getMessage());
        }
        return  new ResponseEntity<>(response, HttpStatus.OK);
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

    @GetMapping("/create")
    public String CreateNew(Model model){
        ArrayList<SurveyQuestion> questions = new ArrayList<SurveyQuestion>();
        model.addAttribute("survey", new Survey());
        model.addAttribute("questions", questions);
        return "form-survey";
    }

    @GetMapping("/{surveyId}")
    public String Details(@PathVariable("surveyId")Integer surveyId, Model model){
        model.addAttribute("survey", surveyService.GetById(surveyId));
        System.out.println(surveyService.GetSurveyQuestionBySurveyId(surveyId).stream().count());
        model.addAttribute("questions", surveyService.GetSurveyQuestionBySurveyId(surveyId));
        return "form-survey";
    }

    @PostMapping("/do")
    public ResponseEntity<Object> DoSurvey(){
        HashMap<String, Object> res = new HashMap<>();
        try{
            res.put("code", 0);
        }catch (Exception ex){
            res.put("code", 1);
        }
        return  new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{surveyId}")
    public ResponseEntity<Object> DeleteSurvey(@PathVariable("surveyId")Integer id){
        HashMap<String, Object> res = new HashMap<>();
        try{
            surveyService.Delete(id);
            res.put("code", 0);
            res.put("message", "Delete survey success!");
        }catch (Exception ex){
            res.put("code", 1);
            res.put("message", "Delete survey fail!");
        }
        return  new ResponseEntity<>(res, HttpStatus.OK);
    }
}
