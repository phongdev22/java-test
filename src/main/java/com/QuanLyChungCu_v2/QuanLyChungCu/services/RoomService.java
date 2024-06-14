package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Room;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomService{
    @Autowired
    private RoomRepository repo;

    public List<Room> getAll(){
        return repo.findAll();
    }
    public void addOrUpdate(Room entity){}

    public void Save(Room entity){
            repo.save(entity);
    }

    public Room getRoomById(Integer id){
        return repo.findById(id).get();
    }

    public void Delete(Integer id){
        repo.deleteById(id);
    }
}
