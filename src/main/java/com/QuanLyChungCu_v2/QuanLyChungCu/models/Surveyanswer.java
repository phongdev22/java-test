
package com.QuanLyChungCu_v2.QuanLyChungCu.models;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "survey_answer")
@Data
@NoArgsConstructor
public class SurveyAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String textAnswer;
    private Integer questionId;
}