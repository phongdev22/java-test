
package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.EntryRight;
import java.util.List;
import java.util.Map;

public interface EntryRightService {

    void addOrUpdate(EntryRight pr);

    List<EntryRight> getEntryRight(Map<String, String> params);

    EntryRight getEntryRightById(int id);

    void deleteEntryRight(int id);

    int getTotalEntryRights();
}
