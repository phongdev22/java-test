
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyquestion;
import java.util.List;
import java.util.Map;

public interface SurveyQuestionRepository {
    List<Surveyquestion> getSurveyQuestions(Map<String, String> params);

    int addOrUpdate(Surveyquestion surveyQuestion);

    Surveyquestion getSurveyQuestionById(int id);

    void deleteSurveyQuestion(int id);
}
