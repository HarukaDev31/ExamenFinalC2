package com.example.controller;

import com.example.entity.Payroll;
import com.example.repository.PayrollRepository;
import com.example.response.GetPaymentResponse;
import com.example.service.AttendancRecordService;
import com.example.service.PayrollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class PayrollController {
    private final PayrollService payrollService;
    private final PayrollRepository payrollRepository;
    public PayrollController(PayrollService payrollService,
                             PayrollRepository payrollRepository) {
        this.payrollService = payrollService;
        this.payrollRepository = payrollRepository;
    }

    @GetMapping("/generate-payroll")
    public void generatePayroll(@RequestParam int month, @RequestParam int year, String employeeId) {
        payrollService.generatePayroll(month, year, employeeId);
    }
    @GetMapping("/payroll")
    public ResponseEntity<?> getPayroll(String month, String year, Long employeeId) {
        if(month == null || year == null
        //check if month is a number
                || !month.matches("\\d+")
        //check if year is a number
                || !year.matches("\\d+")
        ) {
            return ResponseEntity.badRequest().body("Invalid request");
        }
        int monthC=Integer.parseInt(month);
        int yearC=Integer.parseInt(year);
        List<Payroll> payrollList;
        if(employeeId == null) {
            payrollList = payrollRepository.findByMonthAndYear(monthC, yearC);

        }else{
            payrollList = payrollRepository.findByMonthAndYearAndEmployeeId(monthC, yearC, employeeId);

        }

        GetPaymentResponse response = new GetPaymentResponse(true, null, payrollList);
        return ResponseEntity.ok(response);
    }
}
