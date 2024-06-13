
package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.User;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void blockUser(Model model, @PathVariable("userId") int userId) {
        try {
            userService.blockUser(userId);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ex.getMessage(), ex);
        }
    }

    @PostMapping(path = "/{userId}", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<?> updateUser(@RequestParam Map<String, String> params,
            @RequestPart MultipartFile[] files, @PathVariable("userId") int userId) throws Exception {

        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "User not found with ID: " + userId);
            }

            if (files.length > 0) {
                user.setFile(files[0]);
            }
            if (params.get("password") != null) {
                user.setPassword(params.get("password"));
            }

            userService.addOrUpdate(user);
            return ResponseEntity.status(HttpStatus.OK).body("Update user successful");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ex.getMessage());
        }
    }
}
