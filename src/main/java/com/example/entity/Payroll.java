package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payroll")
@JsonIgnoreProperties({"payroll","salaries","bankAccounts","employees","area","user"})

public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @OneToOne
    @JoinColumn(name = "employee_id")

    private Employee employee;
    private int monthCreated;
    private int yearCreated;
    private double total;
    private LocalDate created_at;
    private LocalDate updated_at;
    @OneToMany(mappedBy = "payroll",fetch = FetchType.LAZY)
    private List<PayrollDetail> payrollDetails;
}
