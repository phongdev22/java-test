package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Query("SELECT i FROM Invoice i WHERE LOWER(i.description) LIKE %:keyword% OR LOWER(i.paymentCode) LIKE %:keyword% OR CAST(i.roomId AS string) LIKE %:keyword%")
    Page<Invoice> findAllByKeyword(@RequestParam("keyword") String keyword, Pageable pageable);

    Invoice findByPaymentCode(String paymentCode);

    Page<Invoice> findByRoomId(Integer id, Pageable pageable);
}