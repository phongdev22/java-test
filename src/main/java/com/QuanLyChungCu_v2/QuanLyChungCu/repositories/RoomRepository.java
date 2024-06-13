
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Room;
import java.util.List;
import java.util.Map;

public interface RoomRepository {

    List<Room> getRooms(Map<String, String> params);

    void addOrUpdate(Room r);

    Room getRoomById(int id);

    void deleteRoom(int id) throws Exception;

    int getTotalRooms();

    boolean isRoomNameExists(String roomName);
}
