package com.springframework.assignmentCMS.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Member")
public class Member{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;

    @Column(length = 255, nullable = false)
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String description;
    private Date createDate;
    private Date updateTime;
    private String username;
    private String password;

    public Member(String firstName, String lastName, String phone,
                  String email, String description, Date createDate,
                  Date updatedTime, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.description = description;
        this.createDate = createDate;
        this.updateTime = updatedTime;
        this.username = userName;
        this.password = password;
    }

    public Member(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.createDate = new Date();
        this.updateTime = new Date();
    }

    public void setInfo(String firstName, String lastName, String phone, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.description = description;
    }
}