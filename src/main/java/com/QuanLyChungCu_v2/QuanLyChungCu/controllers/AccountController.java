package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.dto.AccountDTO;
import com.QuanLyChungCu_v2.QuanLyChungCu.dto.AuthDTO;
import com.QuanLyChungCu_v2.QuanLyChungCu.dto.ResponseData;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Room;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.UserEntity;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.RoomService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.UserEntityService;
import com.QuanLyChungCu_v2.QuanLyChungCu.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.QuanLyChungCu_v2.QuanLyChungCu.utils.Utils.saveFile;

@Controller
@RequestMapping("/users")
public class AccountController {
    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String Index() {
        return "page-account";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new UserEntity());
        model.addAttribute("rooms", roomService.findAll());
        return "form-account";
    }

    @GetMapping("/{userId}")
    public String updateRoomView(Model model, @PathVariable("userId") int userId) {
        System.out.println(userId);
        model.addAttribute("user", userEntityService.findById(userId));
        model.addAttribute("rooms", roomService.findAll());
        return "form-account";
    }

    // @GetMapping("/profile/{userId}") @PathVariable("userId")Integer userId
    @GetMapping("/profile")
    public String getProfile(Model model) {
        UserEntity user = getUserAuthencated();
        if(user == null){
            return "redirect:login";
        }
        model.addAttribute("user", user);
        return "page-profile";
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> saveOrUpdateUser(@ModelAttribute UserEntity user) {
        Map<String, String> response = new HashMap<>();
        try {
            user.setPassword(passwordEncoder.encode("123456"));
            user.setUsername(user.getPhone());
            user.setRoleName("USER");
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

    @PostMapping("/change-password")
    public ResponseEntity<ResponseData> changePassword(@RequestParam String password) {
        try {
            UserEntity user = getUserAuthencated();
            if(user != null){
                user.setPassword(passwordEncoder.encode(password));
                userEntityService.Save(user);
            }
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

    @PostMapping("/upload-avatar")
    public ResponseEntity<?> handleFileUpload(@RequestParam("avatar") MultipartFile file) {
        try {
            UserEntity user = getUserAuthencated();
            System.out.println(user);
            if(user != null){
                String fileName = user.getUsername() + "_" + file.getOriginalFilename();
                String folder = "avatars";
                saveFile(file, folder, fileName);
                String avatarUrl = "/images/" + folder + "/" + fileName;
                user.setAvatar(avatarUrl);
                userEntityService.Save(user);
            }

            return ResponseEntity.ok(new ResponseData(0, "Update successfully", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData(1, "Update fail!", null));
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

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Object> DeleteAccount(@PathVariable("userId")Integer id){
        HashMap<String, Object> res = new HashMap<>();
        try{
            userEntityService.Delete(id);
            res.put("code", 0);
            res.put("message", "Delete survey success!");
        }catch (Exception ex){
            res.put("code", 1);
            res.put("message", "Delete survey fail!");
        }
        return  new ResponseEntity<>(res, HttpStatus.OK);
    }

    private UserEntity getUserAuthencated(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userEntityService.findByUserName(username);
        }
        return null;
    }
}
