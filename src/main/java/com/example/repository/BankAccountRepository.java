package com.example.repository;

import com.example.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankAccountRepository  extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByEmployeeId(Long userId);
    @Query("SELECT b FROM BankAccount b WHERE b.employee.id = ?1 and b.isActive = true")
    List<BankAccount> findAllByEmployeeId(Long userId);
}