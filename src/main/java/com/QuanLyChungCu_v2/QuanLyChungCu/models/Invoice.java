
package com.QuanLyChungCu_v2.QuanLyChungCu.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "invoice")
@Data
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public String paymentCode;
    private String description;
    private BigDecimal amount;
    private String status;
    private Date dueDate;

    private Date paymentDate;
    public Boolean isPaid;

    private String serviceType;

    private Integer roomId;
    private Integer userId; // User ID của người thanh toán

    private Date createdAt;
    private Date updatedAt;
}