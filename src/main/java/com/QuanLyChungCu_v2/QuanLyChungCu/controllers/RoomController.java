
package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Room;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.RoomService;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/")
    public String addRoomView(Model model, Principal p) {
        model.addAttribute("room", new Room());

        return "room-list";
    }

    @GetMapping("/{roomId}")
    public String updateRoomView(Model model, @PathVariable("roomId") int roomId) {
        model.addAttribute("room", roomService.getRoomById(roomId));

        return "room-list";
    }

    @PostMapping("/")
    public String addRoomProcess(Model model, @ModelAttribute(value = "room") @Valid Room room,
            BindingResult rs) {
        if (!rs.hasErrors() || room.getId() != null) {
            try {
                roomService.addOrUpdate(room);

                return "redirect:/";
            } catch (Exception ex) {
                model.addAttribute("errMsg", ex.toString());
                System.err.println("Err: " + ex.getMessage());
            }
        }

        return "room-list";
    }
}
