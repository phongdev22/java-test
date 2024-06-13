
package com.QuanLyChungCu_v2.QuanLyChungCu.services.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.StatsRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.StatsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private StatsRepository statsRepo;

    @Override
    public List<Object[]> getReportForSurveyQuestion(int surveyId, int questionId) {
        return statsRepo.getReportForSurveyQuestion(surveyId, questionId);
    }

    @Override
    public List<Object[]> getCountResponseForSurvey() {
        return statsRepo.getCountResponseForSurvey();
    }

}
