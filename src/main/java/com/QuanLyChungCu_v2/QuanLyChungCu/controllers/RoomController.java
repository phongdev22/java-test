
package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.services.MediaService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private MediaService mediaService;

    @GetMapping()
    public String addRoom() {
//        List<Room> list = roomService.getAll();
//        System.out.println("List rom"+list.size());
//        model.addAttribute("rooms", list );
        return "page-add-room";
    }

    @GetMapping("/{roomId}")
    public String updateRoomView(Model model, @PathVariable("roomId") int roomId) {
        model.addAttribute("room", roomService.getRoomById(roomId));
        model.addAttribute("media", mediaService.findByMapping(roomId, "Room", "Image" ));
        return "list-room";
    }
}
