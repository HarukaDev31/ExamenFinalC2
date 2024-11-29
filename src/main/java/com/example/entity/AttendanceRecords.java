package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attendance_records")
public class AttendanceRecords {
    @Id
    private Long id;
    @JsonIgnoreProperties({"bankAccounts", "salaries","employee"})

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private LocalDateTime createdAt;
}
