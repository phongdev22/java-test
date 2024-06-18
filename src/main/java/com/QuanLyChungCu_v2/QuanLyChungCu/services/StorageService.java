package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Item;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StorageService {
    @Autowired
    private ItemRepository repo;

    public Page<Item> getAll(Pageable pageable){
        return repo.findAll(pageable);
    }

    public Page<Item> searchByKeyword(String keyword, Pageable pageable){
        if (keyword == null || keyword.isEmpty()) {
            return repo.findAll(pageable);
        } else {
            return repo.findByKeyword(keyword, pageable);
        }
    }

    public List<Item> GetItemsByUserId(Integer userId){
        return repo.getItemsByUserId(userId);
    }

    public void SaveItemInStorage(Item item){
        repo.save(item);
    }

    public void UpdateStatusItemInStorage(Integer itemId){
        Item itemToUpdate = repo.findById(itemId).orElse(null);

        if (itemToUpdate != null) {
            itemToUpdate.setStatus(!itemToUpdate.isStatus());
            repo.save(itemToUpdate);
        } else {
            throw new RuntimeException("Không tìm thấy Item với ID: " + itemId);
        }
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }
}
