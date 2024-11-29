package com.example.controller;

import com.example.repository.SettingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class SettingController {
    private  final SettingRepository settingRepository;
    public SettingController(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }
    @GetMapping("/settings")
    public ResponseEntity<?> getSettings(String key) {
        if(key == null || key.isEmpty()) {
            return ResponseEntity.ok(settingRepository.findAll());
        }
        else {
            return ResponseEntity.ok(settingRepository.findByKeyVal(key));
        }
    }
}
