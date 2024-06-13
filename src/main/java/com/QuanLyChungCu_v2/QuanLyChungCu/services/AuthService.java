
package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.dto.AuthRequest;
import com.QuanLyChungCu_v2.QuanLyChungCu.dto.AuthResponse;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    AuthResponse authenticate(AuthRequest request, HttpServletResponse response);

    AuthResponse refreshAccessToken(String oldRefreshToken) throws Exception;
}
