package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "settings")
public class Setting {
    @Id
    private Long id;
    private String name;
    private String keyVal;
    private double value;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
