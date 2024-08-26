package com.StudentTeacherAPI.service;

import com.StudentTeacherAPI.Repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentAddressService {
    private final StudentRepository studentRepository;

    public StudentAddressService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


}
