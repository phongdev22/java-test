
package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyquestion;
import java.util.List;
import java.util.Map;

public interface SurveyQuestionService {

    List<Surveyquestion> getSurveyQuestions(Map<String, String> params);

    int addOrUpdate(Surveyquestion surveyQuestion);

    Surveyquestion getSurveyQuestionById(int id);

    void deleteSurveyQuestion(int id);

}
