
package com.QuanLyChungCu_v2.QuanLyChungCu.services.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Roomtype;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.RoomTypeRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.RoomTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepo;

    @Override
    public List<Roomtype> getRoomtypes() {
        return roomTypeRepo.getRoomtypes();
    }

}
