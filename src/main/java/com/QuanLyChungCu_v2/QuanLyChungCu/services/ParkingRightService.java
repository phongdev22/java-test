
package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.ParkingRight;
import java.util.List;
import java.util.Map;

public interface ParkingRightService {

    void addOrUpdate(ParkingRight pr);

    List<ParkingRight> getParkingRight(Map<String, String> params);

    ParkingRight getParkingRightById(int id);

    void deleteParkingRight(int id);

    int getTotalParkingRights();
}
