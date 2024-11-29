package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SalaryHistory {
    private Long id;
    private Employee employee;
    private double amount;
    private Position position;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date updatedAt;
}
