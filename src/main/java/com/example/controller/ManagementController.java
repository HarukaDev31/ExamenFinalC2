package com.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/management")
@RequiredArgsConstructor
public class ManagementController {

    @PostMapping
    public String post() {
        return "POST:: management controller";
    }

    @GetMapping
    public String get() {
        return "GET:: management controller";
    }

    @PutMapping
    public String put() {
        return "PUT:: management controller";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE:: management controller";
    }
}