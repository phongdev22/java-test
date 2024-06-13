
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Locker;
import java.util.List;
import java.util.Map;

public interface LockerRepository {
    List<Locker> getLockers(Map<String, String> params);

    void addOrUpdate(Locker locker);

    Locker getLockerById(int id);

    void deleteLocker(int id) throws Exception;

    int getTotalLockers();
}
