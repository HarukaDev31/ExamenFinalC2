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
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    private Long id;
    @JsonIgnoreProperties({"bankAccounts", "salaries","employee"})
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private String accountNumber;
    private String interbankCode;
    private String institution;
    private Boolean isActive;
}
