
package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyresponse;
import java.util.List;
import java.util.Map;

public interface SurveyResponseService {
    List<Surveyresponse> getSurveys(Map<String, String> params);

    int addSurveyResponse(Surveyresponse surveyResponse);

    Surveyresponse getSurveyResponseById(int id);
}
