package com.example.controller;

import com.example.entity.Salary;
import com.example.entity.SalaryHistory;
import com.example.response.GetSalariesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.SalaryRepository;

import java.util.List;

@RequestMapping("/api")
@RestController
public class SalaryController {
    private final SalaryRepository salaryRepository;
    public SalaryController(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }
    @GetMapping("/salaries")
    public ResponseEntity<?> getSalaries(String search) {

        List<Salary> salaries;
        if(!(search == null || search.isEmpty())) {
            salaries = salaryRepository.findBySearch(search);
        }
        else {
            salaries = salaryRepository.findAll();
        }
        GetSalariesResponse response = new GetSalariesResponse(true, "", salaries);
        return ResponseEntity.ok(response);
    }
}
