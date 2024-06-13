package com.QuanLyChungCu_v2.QuanLyChungCu.services.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.dto.AuthRequest;
import com.QuanLyChungCu_v2.QuanLyChungCu.dto.AuthResponse;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.User;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.AuthService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.JWTService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Override
    public AuthResponse authenticate(AuthRequest request, HttpServletResponse response) {
        try {
            User user = userService.getUserByUsername(request.getUsername());

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            String jwtAccessToken = jwtService.generateToken(user);

            response.setHeader("Authentication", "Bearer " + jwtAccessToken);

            return AuthResponse.builder()
                    .accessToken(jwtAccessToken)
                    .user(user)
                    .build();
        } catch (AuthenticationException e) {
            Logger.getLogger(AuthServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    @Override
    public AuthResponse refreshAccessToken(String oldRefreshToken) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
