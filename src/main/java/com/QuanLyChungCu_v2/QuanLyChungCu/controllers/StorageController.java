package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Item;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @GetMapping("/{userId}")
    public String GetAllItems(@PathVariable Integer userId) {
        storageService.GetItemsByUserId(userId);

        return "form-storage";
    }

    @GetMapping()
    public String getAll(@RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "10") int pageSize,
            Model model) {
        return "page-storage";
    }

    @GetMapping("/create")
    public String createRoom(Model model) {
        model.addAttribute("item", new Item());
        return "form-storage";
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> saveOrUpdateStorage(@ModelAttribute Item item) {
        Map<String, String> response = new HashMap<>();
        try {
            storageService.SaveItemInStorage(item);
            response.put("code", "1");
            response.put("message", "Save successfully!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.put("code", "0");
            response.put("message", "Error occurred!");
            System.out.println("Update or Add failed!" + ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/list")
    public String getAllStorage(@RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "7") int pageSize,
            @RequestParam(defaultValue = "") String keyword,
            Model model) {
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Item> itemPage;
        if (keyword == null || keyword.isEmpty()) {
            itemPage = storageService.getAll(pageable);
        } else {
            itemPage = storageService.searchByKeyword(keyword, pageable);
        }

        model.addAttribute("items", itemPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", itemPage.getTotalPages());
        return "list-items";
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<Map<String, String>> deleteRoom(@PathVariable("itemId") int itemId) {
        Map<String, String> response = new HashMap<>();
        try {
            storageService.delete(itemId);
            response.put("code", "0");
            response.put("message", "Delete successfully!");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.put("code", "1");
            response.put("message", "Occurred error ");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
