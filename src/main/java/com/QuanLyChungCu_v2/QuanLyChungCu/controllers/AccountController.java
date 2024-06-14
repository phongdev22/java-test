package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.UserEntity;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.UserEntityService;
import com.QuanLyChungCu_v2.QuanLyChungCu.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/users")
public class AccountController {
    @Autowired
    private UserEntityService userEntityService;

    @PostMapping("/api/create")
    public ResponseEntity<String> createUser(@RequestBody UserEntity entity) {
        try {
            userEntityService.Save(entity);
            return ResponseEntity.ok("User created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/api/{userId}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable Integer userId, @RequestBody String newPassword) {
        try {
            UserEntity entity = new UserEntity();
            entity.setId(userId);
            userEntityService.UpdateProfile(entity);
            return ResponseEntity.ok("Password updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // update for only role user
    @PostMapping("/api/update-status-account/{userId}")
    public ResponseEntity<String> lockAccount(@PathVariable Integer userId){
        try {
            UserEntity entity = new UserEntity();
            entity.setLock(!entity.isLock());
            userEntityService.UpdateProfile(entity);
            return ResponseEntity.ok("Change status account successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{userId}/upload-avatar")
    public ResponseEntity<String> handleFileUpload(@PathVariable Integer userId, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }
        try {
            String fileName = "avatar_" + userId;

            userEntityService.UpdateAvatar(userId, fileName);
            Utils.saveFile(file,"avatar", fileName);

            return ResponseEntity.ok("Avatar uploaded successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload avatar");
        }
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session) {

        boolean isAuthenticated = true ; // userEntityService.authenticate(username, password);
        if (isAuthenticated) {
            session.setAttribute("username", username);
            return "redirect:/dashboard";
        } else {
            return "redirect:/login?error";
        }
    }

}
