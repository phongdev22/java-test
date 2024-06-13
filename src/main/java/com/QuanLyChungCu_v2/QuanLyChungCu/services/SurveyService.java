
package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Survey;
import java.util.List;
import java.util.Map;

public interface SurveyService {
    List<Survey> getSurveys(Map<String, String> params);

    int addOrUpdate(Survey survey);

    Survey getSurveyById(int id);

    int getTotalSurveys();
}
