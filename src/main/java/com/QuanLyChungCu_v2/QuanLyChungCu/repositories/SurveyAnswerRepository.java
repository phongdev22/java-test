
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyanswer;
import java.util.List;
import java.util.Map;

public interface SurveyAnswerRepository {
    List<Surveyanswer> getSurveyAnswers(Map<String, String> params);

    void addSurveyAnswer(Surveyanswer surveyAnswer);
}
