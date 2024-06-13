
package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import java.util.List;

public interface StatsService {

    List<Object[]> getReportForSurveyQuestion(int surveyId, int questionId);

    List<Object[]> getCountResponseForSurvey();
}
