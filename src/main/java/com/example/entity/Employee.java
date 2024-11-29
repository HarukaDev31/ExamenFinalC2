package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
//add one to one relationship with user
@JsonIgnoreProperties({
        "employee",
        "payrollDetails",
        "payrolls",
        "bankAccounts",
        "salaries",
        "payrollDetail",
        "payroll",

})
public class Employee {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    @Column(name = "is_active")
    private Boolean active;
    private Boolean gender;
    private String hireDate;
    private String terminationDate;
    private String phoneNumber;
    private String address;
    private String documentNumber;
    private int documentTypeId;
    private String createdAt;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;
    @OneToOne
    private Area area;
    private Long pensionId;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Salary> salaries;
    private Boolean hasLoan;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
