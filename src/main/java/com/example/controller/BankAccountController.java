package com.example.controller;

import com.example.entity.BankAccount;
import com.example.exceptions.NotFoundException;
import com.example.repository.BankAccountRepository;
import org.apache.coyote.BadRequestException;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class BankAccountController {
    private final BankAccountRepository bankAccountRepository;
    public BankAccountController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }
    @GetMapping("/bank-accounts")
    public ResponseEntity<?> getBankAccountsByUserId(Long employeeId){
        if(employeeId == null){
            return ResponseEntity.ok(bankAccountRepository.findAll());
        }
        return ResponseEntity.ok(bankAccountRepository.findAllByEmployeeId(employeeId));

    }
    //get current employee bank account
    @GetMapping("/bank-accounts/current")
    public ResponseEntity<?> getCurrentEmployeeBankAccount(Long employeeId) throws BadRequestException {
        if(employeeId == null){
           throw new BadRequestException("Employee id is required");
        }
        List<BankAccount> bankAccounts = bankAccountRepository.findByEmployeeId(employeeId);
        if(bankAccounts.isEmpty()){
            throw new NotFoundException("Bank account not found");
        }
        return ResponseEntity.ok(bankAccounts);
    }

}
