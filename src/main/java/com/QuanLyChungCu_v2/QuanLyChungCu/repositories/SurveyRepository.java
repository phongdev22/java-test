package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {
    @Query("SELECT s FROM Survey as s WHERE s.title LIKE %:keyword% OR s.description LIKE %:keyword%")
    Page<Survey> SearchByKeyword(@RequestParam("keyword")String keyword, Pageable pageable);
}
