package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "positions")
@JsonIgnoreProperties("positions")

public class Position {
    @Id
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;
}
