package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Feedback;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.ParkingRight;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.ParkingRightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ParkingRightService {

    @Autowired
    private ParkingRightRepository repo;

    public List<ParkingRight> getAll() {
        return repo.findAll();
    }
}
