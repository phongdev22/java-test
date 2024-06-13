
package com.QuanLyChungCu_v2.QuanLyChungCu.dto;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Survey;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyquestion;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyForm {
    private Survey survey;

    private List<Surveyquestion> questions;
}
