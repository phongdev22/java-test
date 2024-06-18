package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Media;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Room;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.MediaRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomService{
    @Autowired
    private RoomRepository repo;

    @Autowired
    private MediaRepository mediaRepo;

    public Page<Room> getAll(Pageable pageable){
        return repo.findAll(pageable);
    }

    public List<Room> findAll(){
        return repo.findAll();
    }

    public Page<Room> searchByKeyword(String keyword, Pageable pageable){
        if (keyword == null || keyword.isEmpty()) {
            return repo.findAll(pageable);
        } else {
            return repo.findByKeyword(keyword, pageable);
        }
    }

    public void addOrUpdate(Room entity){}

    public Room Save(Room entity){
        return repo.saveAndFlush(entity);
    }

    public Room getRoomById(Integer id){
        return repo.findById(id).get();
    }

    public List<Media> saveMedia(List<Media> lstMedia){
        return mediaRepo.saveAll(lstMedia);
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }
}
