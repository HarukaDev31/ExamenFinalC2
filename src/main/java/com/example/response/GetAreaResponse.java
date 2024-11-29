package com.example.response;

import com.example.entity.Area;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetAreaResponse {
    private boolean success;
    private String error;
    private List<Area> areas;
}
