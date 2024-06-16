package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    @Query("SELECT f FROM Feedback f WHERE f.userId = :userId")
    List<Feedback> getFeedbacksByUserId(Integer userId);

    @Query("SELECT f FROM Feedback f WHERE f.userId = :userId")
    Optional<Feedback> findByIdAndUserId(Integer id, Integer userId);
}
