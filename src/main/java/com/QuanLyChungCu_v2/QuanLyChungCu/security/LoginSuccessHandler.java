package com.QuanLyChungCu_v2.QuanLyChungCu.security;

import com.QuanLyChungCu_v2.QuanLyChungCu.security.service.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if( userDetails.isFirstLogin() ){
            System.out.println("The user " + userDetails.getAuthorities() + " has logged in.");
            response.sendRedirect("/users/profile");
        }

        response.sendRedirect("/");
    }
}
