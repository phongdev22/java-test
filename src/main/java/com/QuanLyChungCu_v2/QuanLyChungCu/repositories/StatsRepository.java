
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import java.util.List;

public interface StatsRepository {

    List<Object[]> getReportForSurveyQuestion(int surveyId, int questionId);

    List<Object[]> getCountResponseForSurvey();
}
