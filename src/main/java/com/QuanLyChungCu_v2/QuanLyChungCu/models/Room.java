
package com.QuanLyChungCu_v2.QuanLyChungCu.models;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "room")
@Data
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer rating;
    private Integer capacity;
    private String roomType;
    private String status;

    private Date CreatedDate;
}