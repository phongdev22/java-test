
package com.QuanLyChungCu_v2.QuanLyChungCu.models;

import java.util.Date;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
public class Payment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private long amount;
    private String bankCode;
    private String bankTranNo;
    private String transactionNo;
    private String createTimeStamp;
    private boolean status;

    private Integer invoiceId;
    private Integer userId;

    private Date createdAt;
}
