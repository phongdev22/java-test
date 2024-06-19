package com.QuanLyChungCu_v2.QuanLyChungCu.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "survey_question")
@Data
@NoArgsConstructor
public class SurveyQuestion  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String questionText;
    private String questionType;
    private Integer surveyId;
    private Integer questionOrder;
}