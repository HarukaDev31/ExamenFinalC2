package com.example.Dto;

import com.example.entity.AttendanceRecords;

public class AttendanceSummary {

    private AttendanceRecords firstRecord;
    private AttendanceRecords lastRecord;
    private long totalHours;

    // Getters and setters
    public AttendanceRecords getFirstRecord() {
        return firstRecord;
    }

    public void setFirstRecord(AttendanceRecords firstRecord) {
        this.firstRecord = firstRecord;
    }

    public AttendanceRecords getLastRecord() {
        return lastRecord;
    }

    public void setLastRecord(AttendanceRecords lastRecord) {
        this.lastRecord = lastRecord;
    }

    public long getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(long totalHours) {
        this.totalHours = totalHours;
    }
}
