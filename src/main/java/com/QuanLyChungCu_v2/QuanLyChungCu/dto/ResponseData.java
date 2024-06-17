package com.QuanLyChungCu_v2.QuanLyChungCu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@AllArgsConstructor
@Data
@Getter
@Setter
public class ResponseData{
    private int code;
    private String message;
    private Optional<Object> data;
}
