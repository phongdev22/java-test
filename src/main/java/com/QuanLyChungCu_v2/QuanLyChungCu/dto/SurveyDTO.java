package com.QuanLyChungCu_v2.QuanLyChungCu.dto;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDTO {
    public Integer id;
    public String title;
    public String description;
    public Date startDate;
    public Date endDate;
    public List<SurveyQuestionDTO> questions;
}
