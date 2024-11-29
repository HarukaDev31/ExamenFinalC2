package com.example.repository;

import com.example.entity.Salary;
import com.example.entity.SalaryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
    //find by name employee,position or area
    @Query("SELECT s FROM Salary s WHERE " +
            "LOWER(CONCAT(s.employee.firstName, ' ', s.employee.lastName)) LIKE %:search% OR s.position.name LIKE %:search% OR s.position.area.name LIKE %:search%")

    List<Salary> findBySearch(@Param("search") String search);
    @Query("SELECT s FROM Salary s WHERE s.employee.id = :employeeId ORDER BY s.createdAt DESC limit 1")
    Salary findLastByEmployeeId(Long employeeId);
    @Query("SELECT s FROM Salary s WHERE s.employee.id = :employeeId " +
            "AND YEAR(s.createdAt) = :year AND MONTH(s.createdAt) = :month")
    List<Salary> findByYearAndMonthAndEmployeeId(
            @Param("year") int year,
            @Param("month") int month,
            @Param("employeeId") Long employeeId
    );

}