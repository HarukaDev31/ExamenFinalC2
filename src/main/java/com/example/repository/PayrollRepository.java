package com.example.repository;

import com.example.entity.Payroll;
import com.example.entity.PayrollDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PayrollRepository extends
        JpaRepository<Payroll, Long> {
    @Query("SELECT p FROM Payroll p WHERE p.employee.id = :employeeId")
    List<Payroll> findByEmployeeId(Long employeeId);
    @Query("SELECT p FROM Payroll p JOIN FETCH p.payrollDetails WHERE p.employee.id = :employeeId")
    List<Payroll> findWithDetailsByEmployeeId(Long employeeId);
    @Query("SELECT p FROM Payroll p WHERE p.monthCreated = :month AND p.yearCreated = :year AND p.employee.id = :employeeId")
    List<Payroll> findByMonthAndYearAndEmployeeId(int month, int year, Long employeeId);
    @Query("SELECT p FROM Payroll p WHERE p.monthCreated = :month AND p.yearCreated = :year")
    List<Payroll> findByMonthAndYear(int month, int year);

}

