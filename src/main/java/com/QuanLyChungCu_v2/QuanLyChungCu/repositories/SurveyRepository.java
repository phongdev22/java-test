
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Survey;
import java.util.List;
import java.util.Map;

public interface SurveyRepository {
    List<Survey> getSurveys(Map<String, String> params);

    int addOrUpdate(Survey survey);

    Survey getSurveyById(int id);

    int getTotalSurveys();
}
