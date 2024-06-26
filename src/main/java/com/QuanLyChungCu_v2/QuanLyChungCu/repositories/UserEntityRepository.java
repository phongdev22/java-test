package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String userName);

    @Query("SELECT u FROM UserEntity u WHERE u.roleName <> 'ADMIN'")
    List<UserEntity> findAllExcludingAdmins();

}
