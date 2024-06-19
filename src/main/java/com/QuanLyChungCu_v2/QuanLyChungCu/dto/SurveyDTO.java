package com.QuanLyChungCu_v2.QuanLyChungCu.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date endDate;

    public List<SurveyQuestionDTO> questions;
}
