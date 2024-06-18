package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query("SELECT i FROM Item i WHERE i.userId = :userId")
    List<Item> getItemsByUserId(Integer userId);

    @Query("SELECT i FROM Item i WHERE " +
            " CAST(i.userId AS string) LIKE %:keyword% OR " +
            "CAST(i.status AS string) LIKE %:keyword%")
    Page<Item> findByKeyword(@RequestParam("keyword") String keyword, Pageable pageable);
}
