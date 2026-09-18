package com.smartstudent.service;

import com.smartstudent.model.AttendanceRecord;
import com.smartstudent.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceService {
    private final List<AttendanceRecord> records = new ArrayList<>();

    public void record(Student student, LocalDate date, boolean present) {
        records.add(new AttendanceRecord(student.getId(), date, present));
        recalculate(student);
    }

    public void recalculate(Student student) {
        List<AttendanceRecord> studentRecords = records.stream()
                .filter(r -> r.studentId().equals(student.getId()))
                .toList();

        if (studentRecords.isEmpty()) {
            student.setAttendancePercent(0);
            return;
        }

        long present = studentRecords.stream()
                .filter(AttendanceRecord::present)
                .count();

        student.setAttendancePercent((present * 100.0) / studentRecords.size());
    }

    public List<AttendanceRecord> getRecords(String studentId) {
        return records.stream()
                .filter(r -> r.studentId().equals(studentId))
                .toList();
    }

    public List<Student> belowThreshold(java.util.Collection<Student> students, double threshold) {
        return students.stream()
                .filter(s -> s.getAttendancePercent() < threshold)
                .toList();
    }
}
