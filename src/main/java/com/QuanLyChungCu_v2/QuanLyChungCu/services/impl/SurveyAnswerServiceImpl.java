
package com.QuanLyChungCu_v2.QuanLyChungCu.services.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyanswer;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.SurveyAnswerRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.SurveyAnswerService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyAnswerServiceImpl implements SurveyAnswerService {

    @Autowired
    private SurveyAnswerRepository surveyAnswerRepo;

    @Override
    public List<Surveyanswer> getSurveyAnswers(Map<String, String> params) {
        return surveyAnswerRepo.getSurveyAnswers(params);
    }

    @Override
    public void addSurveyAnswer(Surveyanswer surveyAnswer) {

        surveyAnswerRepo.addSurveyAnswer(surveyAnswer);
    }

}
