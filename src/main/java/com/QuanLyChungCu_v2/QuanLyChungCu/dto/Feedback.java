package com.QuanLyChungCu_v2.QuanLyChungCu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    private String name;
    private String phone;
    private String email;
    private String feedback;
    private MultipartFile file;
}
