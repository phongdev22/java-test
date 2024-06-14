
package com.QuanLyChungCu_v2.QuanLyChungCu.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "parkingright")
@Data
@NoArgsConstructor
public class ParkingRight{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer familyMemberId;
    private String typeOfVehicle;
    private String licensePlates;
    private String status;
    private Date createdAt;
    private Date updatedAt;
    // private Relative relativeId;
}