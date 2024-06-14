package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Integer> {
    @Query("SELECT m FROM Media m WHERE m.mappingId = :mappingId AND m.tableName = :tableName AND m.type = :type")
    List<Media> findByMapping(Integer mappingId, String tableName, String type);
}
