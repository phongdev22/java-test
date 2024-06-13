
package com.QuanLyChungCu_v2.QuanLyChungCu.formatters;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Invoicetype;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class InvoicetypeFormatter implements Formatter<Invoicetype> {
    @Override
    public String print(Invoicetype t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public Invoicetype parse(String rtId, Locale locale) throws ParseException {
        Invoicetype rt = new Invoicetype();
        rt.setId(Integer.parseInt(rtId));

        return rt;
    }
}
