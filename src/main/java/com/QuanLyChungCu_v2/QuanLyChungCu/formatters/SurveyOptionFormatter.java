
package com.QuanLyChungCu_v2.QuanLyChungCu.formatters;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyoption;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class SurveyOptionFormatter implements Formatter<Surveyoption> {
    @Override
    public String print(Surveyoption t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public Surveyoption parse(String rtId, Locale locale) throws ParseException {
        Surveyoption rt = new Surveyoption();
        rt.setId(Integer.parseInt(rtId));

        return rt;
    }
}
