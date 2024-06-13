
package com.QuanLyChungCu_v2.QuanLyChungCu.dto;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private User user;
    private String accessToken;
    // private String refreshToken;
}
