package com.QuanLyChungCu_v2.QuanLyChungCu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RelativeRegistrationDTO {
    private Integer id;
    private Integer userId;
    private String relativeName;
    private String relativePhone;
    private String relationship;
    private String vehicleRegistrationNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiryDate;
}
