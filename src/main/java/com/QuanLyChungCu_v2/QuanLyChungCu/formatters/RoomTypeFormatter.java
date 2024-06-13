
package com.QuanLyChungCu_v2.QuanLyChungCu.formatters;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Roomtype;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class RoomTypeFormatter implements Formatter<Roomtype> {

    @Override
    public String print(Roomtype t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public Roomtype parse(String rtId, Locale locale) throws ParseException {
        Roomtype rt = new Roomtype();
        rt.setId(Integer.parseInt(rtId));

        return rt;
    }

}
