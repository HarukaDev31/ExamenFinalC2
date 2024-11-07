package com.example.repository;

import com.example.entity.Employee;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //set table to Employee

}
