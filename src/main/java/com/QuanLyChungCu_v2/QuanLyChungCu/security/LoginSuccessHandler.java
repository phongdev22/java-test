package com.QuanLyChungCu_v2.QuanLyChungCu.security;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.UserEntity;
import com.QuanLyChungCu_v2.QuanLyChungCu.security.service.UserDetailsImpl;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private UserEntityService userEntityService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if( userDetails.isFirstLogin() ){
            UserEntity user = userEntityService.findById(userDetails.getUserId());
            user.setFirstLogin(false);
            userEntityService.Save(user);
            response.sendRedirect("/users/profile");
        }
        else {
            response.sendRedirect("/");
        }
    }
}
