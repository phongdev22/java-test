package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Media;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.UserEntity;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.MediaRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.OptionalInt;

@Service
@Transactional
public class UserEntityService {
    @Autowired
    public UserEntityRepository userRepo;

    @Autowired
    private MediaRepository mediaRepo;

    public void Save(UserEntity entity){
        userRepo.save(entity);
    }

    public void Update(UserEntity entity) {
        if (entity.getId() != null && userRepo.existsById(entity.getId())) {
            userRepo.save(entity);
        } else {
            throw new IllegalArgumentException("UserEntity with id " + entity.getId() + " not found.");
        }
    }

    public void UpdateProfile(UserEntity entity){
        if (entity.getId() != null && userRepo.existsById(entity.getId())) {
            userRepo.save(entity);
        } else {
            throw new IllegalArgumentException("UserEntity with id " + entity.getId() + " not found.");
        }
    }

    public void LockAccount(UserEntity entity){
        if (entity.getId() != null && userRepo.existsById(entity.getId())) {
            entity.setLock(true);
            userRepo.save(entity);
        } else {
            throw new IllegalArgumentException("UserEntity with id " + entity.getId() + " not found.");
        }
    }

    public void UnLockAccount(UserEntity entity){
        if (entity.getId() != null && userRepo.existsById(entity.getId())) {
            entity.setLock(false);
            userRepo.save(entity);
        } else {
            throw new IllegalArgumentException("UserEntity with id " + entity.getId() + " not found.");
        }
    }

    public void UpdateAvatar(Integer userId, String source){
        if (userId != null && mediaRepo.existsById(userId) ){
            Media media = mediaRepo.findByMapping(userId, "UserEntity", "Avatar").get(0);

            if(media != null){
                media.setSource(source);
                mediaRepo.save(media);
            }
        } else {
            throw new IllegalArgumentException("Media of user id " + userId + " not found.");
        }
    }
}
