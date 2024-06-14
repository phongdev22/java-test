package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Item;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StorageService {
    @Autowired
    private ItemRepository repo;

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
}
