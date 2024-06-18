
package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Media;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Room;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.MediaService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.RoomService;

import com.QuanLyChungCu_v2.QuanLyChungCu.utils.Utils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private MediaService mediaService;

    @GetMapping()
    public String Index(){
        return "page-room";
    }

    @GetMapping("/list")
    public String getAllRoom(@RequestParam(defaultValue = "1") int currentPage,
                             @RequestParam(defaultValue = "7") int pageSize,
                             @RequestParam(defaultValue = "")String keyword,
                             Model model) {
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Room> roomPage;
        if (keyword == null || keyword.isEmpty()) {
            roomPage = roomService.getAll(pageable);
        } else {
            roomPage = roomService.searchByKeyword(keyword, pageable);
        }

        model.addAttribute("rooms", roomPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", roomPage.getTotalPages());
        return "list-room";
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> saveOrUpdateRoom(@ModelAttribute Room room,
                           @RequestParam(value = "files", required = false) MultipartFile[] files) {

        Map<String, String> response = new HashMap<>();
        try{
            Room savedRoom = roomService.Save(room);

            List<Media> mediaList = new ArrayList<>();

            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    String fileName = file.getOriginalFilename();
                    Utils.saveFile(file, "room-images", fileName);

                    Media media = new Media();
                    media.setType("image");
                    media.setSource("room-images/" + fileName);
                    media.setMappingId(savedRoom.getId());
                    mediaList.add(media);
                }
            }

            List<Media> savedMediaList = roomService.saveMedia(mediaList);

            response.put("code", "1");
            response.put("message", "Save successfully!");
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            response.put("code", "0");
            response.put("message", "Error occurred!");
            System.out.println("Update or Add failed!" + ex.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/upload-file/{roomId}")
    public ResponseEntity<Map<String, String>> uploadFile(@PathVariable("roomId")int roomId) {
        Map<String, String> response = new HashMap<>();
        try{
            response.put("code", "1");
            response.put("message", "Save successfully!");
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            response.put("code", "0");
            response.put("message", "Error occurred!");
            System.out.println("Update or Add failed!" + ex.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/create")
    public String createRoom(Model model) {
        model.addAttribute("room", new Room());
        return "form-room";
    }

    @GetMapping("/{roomId}")
    public String updateRoomView(Model model, @PathVariable("roomId") int roomId) {
        model.addAttribute("room", roomService.getRoomById(roomId));
        // model.addAttribute("media", mediaService.findByMapping(roomId, "Room", "Image" ));
        return "form-room";
    }

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<Map<String, String>> deleteRoom(@PathVariable("roomId") int roomId) {
        Map<String, String> response = new HashMap<>();
        try{
            roomService.delete(roomId);
            response.put("code", "0");
            response.put("message", "Delete successfully!");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception ex){
            response.put("code", "1");
            response.put("message", "Occurred error ");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
