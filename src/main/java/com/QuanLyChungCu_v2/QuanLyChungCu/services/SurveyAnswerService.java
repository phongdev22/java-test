
package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyanswer;
import java.util.List;
import java.util.Map;

public interface SurveyAnswerService {
    List<Surveyanswer> getSurveyAnswers(Map<String, String> params);

    void addSurveyAnswer(Surveyanswer surveyAnswer);
}
