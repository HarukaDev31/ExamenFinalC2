package com.example.repository;

import com.example.entity.Employee;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //set table to Employee
    @Query("SELECT e FROM Employee e JOIN e.user u WHERE " +
            "LOWER(CONCAT(e.firstName, ' ', e.lastName)) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Employee> findBySearch(@Param("search") String search);
    @Query("SELECT e FROM Employee e JOIN e.user u WHERE u.id = :id")
    List<Employee> findByEmployeeId(Long id);
}
