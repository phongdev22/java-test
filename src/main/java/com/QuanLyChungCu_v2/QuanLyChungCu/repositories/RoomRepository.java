package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE " +
            "r.code LIKE %:keyword% OR " +
            "r.name LIKE %:keyword% OR " +
            "r.description LIKE %:keyword% OR " +
            "CAST(r.price AS string) LIKE %:keyword% OR " +
            "CAST(r.rating AS string) LIKE %:keyword% OR " +
            "CAST(r.capacity AS string) LIKE %:keyword% OR " +
            "r.roomType LIKE %:keyword%")
    Page<Room> findByKeyword(@RequestParam("keyword") String keyword, Pageable pageable);

}
