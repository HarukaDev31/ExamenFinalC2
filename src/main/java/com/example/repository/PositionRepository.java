package com.example.repository;

import com.example.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findByNameContaining(String nome);
}
