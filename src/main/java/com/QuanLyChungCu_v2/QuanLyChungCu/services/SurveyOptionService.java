
package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyoption;
import java.util.List;
import java.util.Map;

public interface SurveyOptionService {
    List<Surveyoption> getSurveyOptions(Map<String, String> params);

    int addOrUpdate(Surveyoption surveyOption);

    Surveyoption getSurveyOptionById(int id);

    void deleteSurveyOption(int id);
}
