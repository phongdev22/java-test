
package com.QuanLyChungCu_v2.QuanLyChungCu.formatters;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Locker;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class LockerFormatter implements Formatter<Locker> {

    @Override
    public String print(Locker l, Locale locale) {
        return String.valueOf(l.getId());
    }

    @Override
    public Locker parse(String lockerId, Locale locale) throws ParseException {
        Locker l = new Locker();
        l.setId(Integer.parseInt(lockerId));

        return l;
    }

}
