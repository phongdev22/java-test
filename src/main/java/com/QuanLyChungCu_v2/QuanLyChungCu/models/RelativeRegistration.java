
package com.QuanLyChungCu_v2.QuanLyChungCu.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "relative_registration")
@Data
@NoArgsConstructor
public class RelativeRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String relativeName;
    private String relativePhone;
    private String relationship;
    private String vehicleRegistrationNumber;
    private Date registrationDate;
    private Date expiryDate;
    private String status;
    private Date createdAt;
    private Date updatedAt;
}