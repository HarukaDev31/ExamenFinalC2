package com.example.controller;

import com.example.entity.Employee;
import com.example.entity.Position;
import com.example.repository.EmployeeRepository;
import com.example.repository.PositionRepository;
import com.example.response.GetEmployeeResponse;
import com.example.response.GetPositionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/api")
@RestController

public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    //function to get employees and filter by search value in route and filter by name lastname concat or email
    public GetEmployeeResponse getEmployees(String search) {
        GetEmployeeResponse employees = new GetEmployeeResponse(true, null, employeeRepository.findBySearch(search));

        return employees;
    }

}