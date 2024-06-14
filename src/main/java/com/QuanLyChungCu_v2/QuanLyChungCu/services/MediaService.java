package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Media;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MediaService {
    @Autowired
    private MediaRepository repo;

    public List<Media> findByMapping(Integer mappingId, String tableName, String type) {
        return repo.findByMapping(mappingId, tableName, type);
    }

    public Media Save(Media media) {
        return repo.save(media);
    }

    public Media Update(Media media){
        if (media.getId() != null && repo.existsById(media.getId())) {
            return repo.save(media);
        }
        return null;
    }

    public void Delete(Integer id){
        repo.deleteById(id);
    }
}
