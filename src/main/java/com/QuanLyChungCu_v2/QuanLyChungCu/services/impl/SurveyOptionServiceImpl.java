
package com.QuanLyChungCu_v2.QuanLyChungCu.services.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyoption;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.SurveyOptionRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.SurveyOptionService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyOptionServiceImpl implements SurveyOptionService {

    @Autowired
    private SurveyOptionRepository surveyOptionRepo;

    @Override
    public List<Surveyoption> getSurveyOptions(Map<String, String> params) {
        return surveyOptionRepo.getSurveyOptions(params);
    }

    @Override
    public int addOrUpdate(Surveyoption surveyOption) {
        return surveyOptionRepo.addOrUpdate(surveyOption);
    }

    @Override
    public Surveyoption getSurveyOptionById(int id) {
        return surveyOptionRepo.getSurveyOptionById(id);
    }

    @Override
    public void deleteSurveyOption(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
