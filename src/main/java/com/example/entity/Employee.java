package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
//add one to one relationship with user

public class Employee {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private Boolean gender;
    private String hireDate;
    private String terminationDate;
    private String phoneNumber;
    private String address;
    private String documentNumber;
    private int documentTypeId;
    private String createdAt;
    @JsonIgnoreProperties("employee")

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;





    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
