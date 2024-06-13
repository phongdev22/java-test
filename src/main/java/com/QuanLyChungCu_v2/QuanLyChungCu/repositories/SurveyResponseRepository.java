
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyresponse;
import java.util.List;
import java.util.Map;

public interface SurveyResponseRepository {
    List<Surveyresponse> getSurveys(Map<String, String> params);

    int addSurveyResponse(Surveyresponse surveyResponse);

    Surveyresponse getSurveyResponseById(int id);
}
