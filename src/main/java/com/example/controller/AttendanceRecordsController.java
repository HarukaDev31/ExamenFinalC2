package com.example.controller;

import com.example.Dto.AttendanceSummary;
import com.example.entity.AttendanceRecords;
import com.example.exceptions.NotFoundException;
import com.example.repository.AttendanceRecordsRepository;
import com.example.service.AttendancRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class AttendanceRecordsController {
    private final AttendancRecordService attendancRecordService;

    public AttendanceRecordsController(AttendanceRecordsRepository attendanceRecordsRepository, AttendancRecordService attendancRecordService) {
        this.attendancRecordService = attendancRecordService;
    }
    @GetMapping("/attendance-records")
    public ResponseEntity<?> getAttendanceRecords(Long employeeId) {
        List< AttendanceSummary> attendanceRecords;
        if(employeeId == null) {
            throw new NotFoundException("Employee not found");
        }
        else {
            attendanceRecords = attendancRecordService.getAttendanceSummary(employeeId);
        }
        return ResponseEntity.ok(attendanceRecords);
    }
}
