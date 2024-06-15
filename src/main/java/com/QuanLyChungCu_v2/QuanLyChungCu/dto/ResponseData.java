package com.QuanLyChungCu_v2.QuanLyChungCu.dto;

import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class ResponseData{
    private int code;
    private String message;
    private Optional<Object> data;
}
