
package com.QuanLyChungCu_v2.QuanLyChungCu.services.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.ParkingRight;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.ParkingRightRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.ParkingRightService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingRightServiceImpl implements ParkingRightService {

    @Autowired
    private ParkingRightRepository parkingRightRepo;

    @Override
    public void addOrUpdate(ParkingRight pr) {
        parkingRightRepo.addOrUpdate(pr);
    }

    @Override
    public List<ParkingRight> getParkingRight(Map<String, String> params) {
        return parkingRightRepo.getParkingRight(params);
    }

    @Override
    public ParkingRight getParkingRightById(int id) {
        return parkingRightRepo.getParkingRightById(id);
    }

    @Override
    public void deleteParkingRight(int id) {
        parkingRightRepo.deleteParkingRight(id);
    }

    @Override
    public int getTotalParkingRights() {
        return parkingRightRepo.getTotalParkingRights();
    }

}
