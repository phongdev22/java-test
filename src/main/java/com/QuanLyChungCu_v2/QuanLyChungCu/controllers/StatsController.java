
package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyquestion;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.StatsService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.SurveyQuestionService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StatsController {

    @Autowired
    private StatsService statsService;
    @Autowired
    private SurveyQuestionService surveyQuestionService;

    @GetMapping("/stats")
    public String addStatsView(Model model, @RequestParam Map<String, String> params) {

        List<Object[]> statsResponsesForPerSurvey = statsService.getCountResponseForSurvey();
        model.addAttribute("statsResponsesForPerSurvey", statsResponsesForPerSurvey);

        String surveyId = params.getOrDefault("surveyId", "1");
        String questionId = params.getOrDefault("questionId", "1");

        Map<String, String> questionParams = new HashMap<>();
        questionParams.put("surveyId", surveyId);
        List<Surveyquestion> surveyQuestions = surveyQuestionService.getSurveyQuestions(questionParams);
        model.addAttribute("questions", surveyQuestions);

        List<Object[]> statsCountQuestion = statsService.getReportForSurveyQuestion(
                Integer.parseInt(surveyId), Integer.parseInt(questionId));
        model.addAttribute("statsCountQuestion", statsCountQuestion);

        return "stats";
    }

}
