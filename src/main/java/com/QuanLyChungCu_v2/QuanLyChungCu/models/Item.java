package com.QuanLyChungCu_v2.QuanLyChungCu.models;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "item")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Boolean status;
    private Date createdAt;
    private Date updatedAt;
    private String image;
}
