
package com.QuanLyChungCu_v2.QuanLyChungCu.services.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Locker;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.LockerRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.LockerService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LockerServiceImpl implements LockerService {

    @Autowired
    private LockerRepository lockerRepo;

    @Override
    public List<Locker> getLockers(Map<String, String> params) {
        return lockerRepo.getLockers(params);
    }

    @Override
    public void addOrUpdate(Locker locker) {
        if (locker.getId() == null) {
            locker.setStatus("Blank");
        }
        lockerRepo.addOrUpdate(locker);
    }

    @Override
    public Locker getLockerById(int id) {
        return lockerRepo.getLockerById(id);
    }

    @Override
    public void deleteLocker(int id) throws Exception {
        lockerRepo.deleteLocker(id);
    }

    @Override
    public int getTotalLockers() {
        return lockerRepo.getTotalLockers();
    }

}
