
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.EntryRight;
import java.util.List;
import java.util.Map;

public interface EntryRightRepository {

    void addOrUpdate(EntryRight pr);

    List<EntryRight> getEntryRight(Map<String, String> params);

    EntryRight getEntryRightById(int id);

    void deleteEntryRight(int id);

    int getTotalEntryRights();
}
