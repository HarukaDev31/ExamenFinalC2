package com.example.controller;

import com.example.entity.Position;
import com.example.repository.PositionRepository;
import com.example.response.GetPositionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class PositionController {
    private  final PositionRepository positionRepository;
    public PositionController(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }
    @GetMapping("/positions")
    public ResponseEntity<?> getPositionsByName(String search){
        GetPositionResponse positions;
        if(!(search == null || search.isEmpty())) {
            positions = new GetPositionResponse( positionRepository.findByNameContaining(search));
        }
        else {
            positions = new GetPositionResponse(positionRepository.findAll());
        }
        return ResponseEntity.ok(positions);
    }

}
