package com.example.controller;

import com.example.repository.AreaRepository;
import com.example.response.GetAreaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class AreaController {
    private final AreaRepository areaRepository;
    public AreaController(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }
    @GetMapping("/areas")
    public ResponseEntity<?> getAreas() {
        GetAreaResponse areas = new GetAreaResponse(true, null, areaRepository.findAll());
        return ResponseEntity.ok(areas);
    }
}
