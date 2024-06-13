
package com.QuanLyChungCu_v2.QuanLyChungCu.services.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.EntryRight;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.EntryRightRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.EntryRightService;

@Service
public class EntryRightServiceImpl implements EntryRightService {

    @Autowired
    private EntryRightRepository parkingRightRepo;

    @Override
    public void addOrUpdate(EntryRight pr) {
        parkingRightRepo.addOrUpdate(pr);
    }

    @Override
    public List<EntryRight> getEntryRight(Map<String, String> params) {
        return parkingRightRepo.getEntryRight(params);
    }

    @Override
    public EntryRight getEntryRightById(int id) {
        return parkingRightRepo.getEntryRightById(id);
    }

    @Override
    public void deleteEntryRight(int id) {
        parkingRightRepo.deleteEntryRight(id);
    }

    @Override
    public int getTotalEntryRights() {
        return parkingRightRepo.getTotalEntryRights();
    }

}
