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

    public List<Media> findByMapping(Integer mappingId, String type) {
        return repo.findByMapping(mappingId, type);
    }

    public List<Media> saveMedia(List<Media> lstMedia){
        return repo.saveAll(lstMedia);
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
