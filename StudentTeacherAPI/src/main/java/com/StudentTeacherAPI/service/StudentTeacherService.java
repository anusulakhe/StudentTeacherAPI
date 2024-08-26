package com.StudentTeacherAPI.service;

import com.StudentTeacherAPI.DTO.StudentTeacherDTO;
import com.StudentTeacherAPI.Repositories.StudentRepository;
import com.StudentTeacherAPI.Repositories.StudentTeacherRepository;
import com.StudentTeacherAPI.Repositories.TeacherRepository;
import com.StudentTeacherAPI.model.Student;
import com.StudentTeacherAPI.model.StudentTeacher;
import com.StudentTeacherAPI.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentTeacherService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentTeacherRepository studentTeacherRepository;

    public StudentTeacherService(StudentRepository studentRepository, TeacherRepository teacherRepository, StudentTeacherRepository studentTeacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.studentTeacherRepository = studentTeacherRepository;
    }

    public Boolean MapStudentTeacher (Long studentid, Long teacherid){
        Student student = studentRepository.getReferenceById(studentid);
        Teacher teacher=teacherRepository.getReferenceById(teacherid);
        StudentTeacher studentTeacher= new StudentTeacher();
        studentTeacher.setStudent(student);
        studentTeacher.setTeacher(teacher);
        studentTeacherRepository.save(studentTeacher);
        return  true;
    }
    public List<StudentTeacherDTO> getStudentTeacherDetails() {
        return studentTeacherRepository.findStudentTeacherDetails();

    }

}
