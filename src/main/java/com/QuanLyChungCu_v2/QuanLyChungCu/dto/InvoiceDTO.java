package com.QuanLyChungCu_v2.QuanLyChungCu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceDTO {
    public Integer id;
    public Integer roomId;
    public Integer userId;
    public String paymentCode;
    public String description;
    public BigDecimal amount;
    public String serviceType;
    public Date paymentDate;
    public Boolean isPaid;
}
