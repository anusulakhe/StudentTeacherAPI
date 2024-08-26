package com.StudentTeacherAPI.Repositories;

import com.StudentTeacherAPI.DTO.StudentTeacherDTO;

import java.util.List;

public interface CustomStudentTeacherRepository {
    List<StudentTeacherDTO> findStudentTeacherDetails();
}
