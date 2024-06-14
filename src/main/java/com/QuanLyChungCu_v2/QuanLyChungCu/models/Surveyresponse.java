package com.QuanLyChungCu_v2.QuanLyChungCu.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "survey_response")
@Data
@NoArgsConstructor
public class SurveyResponse{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer surveyId;
    private Date createdAt;
}
