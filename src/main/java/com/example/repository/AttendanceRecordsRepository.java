package com.example.repository;

import com.example.entity.AttendanceRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttendanceRecordsRepository extends JpaRepository<AttendanceRecords, Long> {
    //set table to AttendanceRecords
    @Query("SELECT a FROM AttendanceRecords a WHERE a.employee.id = :employeeId")
    List<AttendanceRecords> findByEmployeeId(Long employeeId);
}
