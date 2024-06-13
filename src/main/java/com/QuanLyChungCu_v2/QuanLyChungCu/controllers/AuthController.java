
package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.dto.AuthRequest;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.User;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.AuthService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.UserService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest, HttpServletResponse response) {

        try {
            User userFind = userService.getUserByUsername(authRequest.getUsername());
            if (userFind == null) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                        "User not found!");
            } else if (!passwordEncoder.matches(authRequest.getPassword(), userFind.getPassword())) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                        "Wrong password");
            } else {
                return ResponseEntity.ok(authService.authenticate(authRequest, response));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when register "
                            + e.getMessage());
        }
    }
}
