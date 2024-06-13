
package com.QuanLyChungCu_v2.QuanLyChungCu.formatters;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Relative;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class RelativeFormatter implements Formatter<Relative> {

    @Override
    public String print(Relative p, Locale locale) {
        return String.valueOf(p.getId());
    }

    @Override
    public Relative parse(String pId, Locale locale) throws ParseException {
        Relative p = new Relative();
        p.setId(Integer.valueOf(pId));

        return p;
    }

}
