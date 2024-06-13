
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Relative;
import java.util.List;
import java.util.Map;

public interface RelativeRepository {

    void addOrUpdate(Relative relative);

    List<Relative> getRelative(Map<String, String> params);

    Relative getRelativeById(int id);

    void deleteRelative(int id);

}
