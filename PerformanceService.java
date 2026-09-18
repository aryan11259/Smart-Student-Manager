package com.smartstudent.service;

import com.smartstudent.model.Student;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class PerformanceService {

    public void addMark(Student student, String subject, double marks) {
        student.addOrUpdateMark(subject, marks);
    }

    public List<Student> topStudents(Collection<Student> students, int limit) {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getAverageMarks).reversed())
                .limit(limit)
                .toList();
    }

    public List<Student> studentsNeedingAttention(Collection<Student> students) {
        return students.stream()
                .filter(s -> s.getAttendancePercent() < 75 || s.getAverageMarks() < 50)
                .sorted(Comparator.comparing(Student::getName))
                .toList();
    }
}
