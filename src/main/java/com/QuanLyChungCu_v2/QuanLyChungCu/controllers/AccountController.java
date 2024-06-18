package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.dto.AccountDTO;
import com.QuanLyChungCu_v2.QuanLyChungCu.dto.AuthDTO;
import com.QuanLyChungCu_v2.QuanLyChungCu.dto.ResponseData;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Room;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.UserEntity;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.UserEntityService;
import com.QuanLyChungCu_v2.QuanLyChungCu.utils.Utils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class AccountController {
    @Autowired
    private UserEntityService userEntityService;

    @GetMapping("")
    public String Index() {
        return "page-account";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginForm") AuthDTO authDTO,
            RedirectAttributes redirectAttributes,
            HttpSession session) {
        String userName = authDTO.getUsername();
        String password = authDTO.getPassword();

        try {
            Object user = userEntityService.login(userName, password);
            session.setAttribute("user", user);
            if (user instanceof UserEntity && ((UserEntity) user).isFirstLogin()) {
                return "redirect:/users/profile";
            } else {
                return "redirect:/";
            }
        } catch (IllegalStateException e) {
            return "redirect:/login";
        }
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new UserEntity());
        return "form-account";
    }

    @GetMapping("/{userId}")
    public String updateRoomView(Model model, @PathVariable("userId") int userId) {
        System.out.println(userId);
        model.addAttribute("user", userEntityService.findById(userId));
        // model.addAttribute("media", mediaService.findByMapping(roomId, "Room",
        // "Image" ));
        return "form-account";
    }

    // @GetMapping("/profile/{userId}") @PathVariable("userId")Integer userId
    @GetMapping("/profile")
    public String getProfile() {
        return "page-profile";
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> saveOrUpdateUser(@ModelAttribute UserEntity user) {
        Map<String, String> response = new HashMap<>();
        try {
            user.setPassword("123456");
            userEntityService.Save(user); // Gọi service để lưu UserEntity
            response.put("code", "1");
            response.put("message", "Save successfully!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.put("code", "0");
            response.put("message", "Error occurred!");
            System.out.println("Save user failed! " + ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{userId}/change-password")
    public ResponseEntity<ResponseData> changePassword(@PathVariable("userId") int userId,
            @RequestBody String newPassword) {
        try {
            UserEntity entity = new UserEntity();
            entity.setId(userId);
            userEntityService.UpdateProfile(entity);
            return ResponseEntity.ok(new ResponseData(0, "Update successfully", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok(new ResponseData(1, "Update fail!", null));
        }
    }

    // update for only role user
    @PostMapping("/update/status/{userId}")
    public ResponseEntity<HashMap<String, String>> changeStatus(@PathVariable("userId") int userId) {
        HashMap<String, String> response = new HashMap<>();
        try {
            userEntityService.UpdateStatus(userId);
            response.put("code", "0");
            response.put("message", "Update successfully!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            response.put("code", "1");
            response.put("message", "Update successfully!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/{userId}/upload-avatar")
    public ResponseEntity<String> handleFileUpload(@PathVariable("userId") int userId,
            @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }
        try {
            String fileName = "avatar_" + userId;

            userEntityService.UpdateAvatar(userId, fileName);
            Utils.saveFile(file, "avatar", fileName);

            return ResponseEntity.ok("Avatar uploaded successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload avatar");
        }
    }

    @GetMapping("/list")
    public String getAll(@RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String keyword,
            Model model) {
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        List<UserEntity> users;

        if (keyword == null || keyword.isEmpty()) {
            users = userEntityService.getAll(pageable).getContent();
        } else {
            users = userEntityService.findAll();
            String finalKeyword = keyword.toLowerCase();
            users.removeIf(user -> !user.getEmail().toLowerCase().contains(finalKeyword) &&
                    !user.getFirstname().toLowerCase().contains(finalKeyword) &&
                    !user.getLastname().toLowerCase().contains(finalKeyword) &&
                    !user.getPhone().toLowerCase().contains(finalKeyword));
        }

        model.addAttribute("users", users);
        return "list-account";
    }
}
