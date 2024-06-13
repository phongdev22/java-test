
package com.QuanLyChungCu_v2.QuanLyChungCu.services.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyquestion;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.SurveyQuestionRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.SurveyQuestionService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyQuestionServiceImpl implements SurveyQuestionService {

    @Autowired
    private SurveyQuestionRepository surveyQuestionRepo;

    @Override
    public List<Surveyquestion> getSurveyQuestions(Map<String, String> params) {
        return surveyQuestionRepo.getSurveyQuestions(params);
    }

    @Override
    public int addOrUpdate(Surveyquestion surveyQuestion) {
        return surveyQuestionRepo.addOrUpdate(surveyQuestion);
    }

    @Override
    public Surveyquestion getSurveyQuestionById(int id) {
        return surveyQuestionRepo.getSurveyQuestionById(id);
    }

    @Override
    public void deleteSurveyQuestion(int id) {
        surveyQuestionRepo.deleteSurveyQuestion(id);
    }

}
