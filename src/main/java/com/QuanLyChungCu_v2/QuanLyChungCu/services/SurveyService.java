package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.dto.SurveyDTO;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.SurveyQuestionRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.SurveyRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.SurveyResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepo;
    @Autowired
    private SurveyQuestionRepository surveyQuestionRepo;
    @Autowired
    private SurveyResponseRepository surveyResponseRepo;


    public void CreateSurvey(SurveyDTO surveyDTO){

    }
}
