
package com.QuanLyChungCu_v2.QuanLyChungCu.models;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "family_member")
@Data
@NoArgsConstructor
public class FamilyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type;
    private String avatar;
    // private Set<ParkingRight> entryrightSet;
    // private Set<EntryRight> parkingrightSet;
    // private UserEntity userId;
    // private MultipartFile file;
}