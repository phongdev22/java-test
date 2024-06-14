package com.QuanLyChungCu_v2.QuanLyChungCu.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "user_entity")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String avatar;
    private String status;
    private String roleName;
    private boolean isLock;

    // private Set<SurveyResponse> surveyresponseSet;
    // private Set<Feedback> feedbackSet;
    // private Set<Payment> paymentSet;
    // private Locker locker;
    // private Room room;
    // private Set<Relative> relativeSet;
    // private MultipartFile file;
}