package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payroll_detail")
@JsonIgnoreProperties({"payroll","salaries","bankAccounts","payrollDetails","employee","payrollDetail","employees","area","user"})
public class PayrollDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "payroll_id")
    private Payroll payroll;
    private String typeKey;
    private Double value;
    private LocalDate createdAt;
}
