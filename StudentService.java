package com.smartstudent.service;

import com.smartstudent.model.Student;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentService {
    private final Map<String, Student> students = new LinkedHashMap<>();

    public void addStudent(Student student) {
        if (students.containsKey(student.getId())) {
            throw new IllegalArgumentException("Student ID already exists.");
        }
        students.put(student.getId(), student);
    }

    public Student getStudent(String id) {
        return students.get(id);
    }

    public boolean updateStudent(String id, String name, String email, String course) {
        Student student = getStudent(id);
        if (student == null) return false;
        student.setName(name);
        student.setEmail(email);
        student.setCourse(course);
        return true;
    }

    public boolean deleteStudent(String id) {
        return students.remove(id) != null;
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public long count() {
        return students.size();
    }

    public void clear() {
        students.clear();
    }

    public java.util.List<Student> search(String keyword) {
        String key = keyword.toLowerCase();
        return students.values().stream()
                .filter(s -> s.getId().toLowerCase().contains(key)
                        || s.getName().toLowerCase().contains(key)
                        || s.getCourse().toLowerCase().contains(key))
                .toList();
    }
}
