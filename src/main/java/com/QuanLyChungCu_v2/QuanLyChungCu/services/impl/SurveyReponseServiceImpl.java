
package com.QuanLyChungCu_v2.QuanLyChungCu.services.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyresponse;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.SurveyResponseRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.SurveyResponseService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyReponseServiceImpl implements SurveyResponseService {

    @Autowired
    private SurveyResponseRepository surveyResponseRepo;

    @Override
    public List<Surveyresponse> getSurveys(Map<String, String> params) {
        return surveyResponseRepo.getSurveys(params);
    }

    @Override
    public int addSurveyResponse(Surveyresponse surveyResponse) {
        surveyResponse.setCreatedAt(new Date());

        return surveyResponseRepo.addSurveyResponse(surveyResponse);
    }

    @Override
    public Surveyresponse getSurveyResponseById(int id) {
        return surveyResponseRepo.getSurveyResponseById(id);
    }

}
