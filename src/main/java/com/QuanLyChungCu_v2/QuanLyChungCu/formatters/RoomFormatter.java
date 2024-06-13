
package com.QuanLyChungCu_v2.QuanLyChungCu.formatters;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Room;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class RoomFormatter implements Formatter<Room> {
    @Override
    public String print(Room r, Locale locale) {
        return String.valueOf(r.getId());
    }

    @Override
    public Room parse(String roomId, Locale locale) throws ParseException {
        Room r = new Room();
        r.setId(Integer.parseInt(roomId));

        return r;
    }
}
