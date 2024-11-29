package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "salaries")

public class Salary {
    @Id
    private Long id;
    @JsonIgnoreProperties({
            "salaries",
            "bankAccounts",
            "area",
            "user"
    })
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
    private Date createdAt;
    private Date updatedAt;
}
