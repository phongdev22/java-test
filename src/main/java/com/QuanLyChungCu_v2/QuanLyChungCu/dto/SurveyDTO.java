package com.QuanLyChungCu_v2.QuanLyChungCu.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
public class SurveyDTO {
    private String title;
    private String description;
    private Set<SurveyQuestionDTO> questions;
    private Set<SurveyAnswerDTO> answers;
}
