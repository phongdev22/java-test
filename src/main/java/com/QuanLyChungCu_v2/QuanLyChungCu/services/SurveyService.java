package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.dto.SurveyDTO;
import com.QuanLyChungCu_v2.QuanLyChungCu.dto.SurveyQuestionDTO;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Survey;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.SurveyQuestion;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.SurveyQuestionRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.SurveyRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.SurveyResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepo;

    @Autowired
    private SurveyQuestionRepository surveyQuestionRepo;

    @Autowired
    private SurveyResponseRepository surveyResponseRepo;

    public Survey Save(SurveyDTO surveyDTO){
        Survey survey = new Survey();
        survey.setTitle(surveyDTO.getTitle());
        survey.setDescription(surveyDTO.getDescription());
        survey.setStartDate(surveyDTO.getStartDate());
        survey.setEndDate(surveyDTO.getEndDate());
        survey.setCreatedDate(new Date());
        survey.setStatus(false);
        survey = surveyRepo.saveAndFlush(survey);

        for (SurveyQuestionDTO questionDTO : surveyDTO.getQuestions()) {
            SurveyQuestion question = new SurveyQuestion();
            question.setQuestionText(questionDTO.getText());
            question.setQuestionType(questionDTO.getQuestionType());
            question.setSurveyId(survey.getId());
            question.setOrder(questionDTO.getOrder());
            surveyQuestionRepo.save(question);
        }

        return survey;
    }

    public Survey GetById(Integer id){
        return surveyRepo.findById(id).get();
    }

    public List<SurveyQuestion> GetSurveyQuestionBySurveyId(Integer id){
        return surveyQuestionRepo.findAllBySurveyId(id);
    }

    public Page<Survey> GetAll(Pageable pageable){
        return surveyRepo.findAll(pageable);
    }

    public Page<Survey> SearchByKeyword(Pageable pageable, String keyword){
        return surveyRepo.SearchByKeyword(keyword, pageable);
    }

    public void Delete(Integer id){
        surveyRepo.deleteById(id);
    }

}
