package com.example.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private int userId;



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
