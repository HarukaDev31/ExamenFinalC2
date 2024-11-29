package com.example.repository;

import com.example.entity.PayrollDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollDetailRepository extends JpaRepository<PayrollDetail, Long> {
    // Métodos adicionales personalizados, si es necesario.
}