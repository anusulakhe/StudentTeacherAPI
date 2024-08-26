package com.StudentTeacherAPI.service;

import com.StudentTeacherAPI.Repositories.*;
import com.StudentTeacherAPI.model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentTeacherRepository studentTeacherRepository;
    private final StudentAddressRepository studentAddressRepository;

    public StudentService(StudentRepository studentRepository, TeacherRepository teacherRepository, StudentTeacherRepository studentTeacherRepository, StudentAddressRepository studentAddressRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.studentTeacherRepository = studentTeacherRepository;
        this.studentAddressRepository = studentAddressRepository;
    }


    @Autowired
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        if (student.getStudentAddresses() != null) {
            for (StudentAddress studentAddress : student.getStudentAddresses()) {
                studentAddress.setStudent(student);
            }
        }
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Long studentId, Student updateStudent) {
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found: " + studentId));

        // Update basic student details
        existingStudent.setFirstName(updateStudent.getFirstName());
        existingStudent.setLastName(updateStudent.getLastName());
        existingStudent.setGender(updateStudent.getGender());
        existingStudent.setStandard(updateStudent.getStandard());
        existingStudent.setEmail(updateStudent.getEmail());

        if (updateStudent.getTeachers() != null) {

            if (!updateStudent.getTeachers().isEmpty()) {
                for (Teacher teacher : updateStudent.getTeachers()) {
                    Teacher existingTeacher = teacherRepository.findById(teacher.getTeacherId())
                            .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + teacher.getTeacherId()));
                    StudentTeacher studentTeacher = new StudentTeacher(existingStudent, existingTeacher);
                    studentTeacherRepository.save(studentTeacher);
                }
            }

        }

        if (updateStudent.getStudentAddresses() != null) {
            existingStudent.getStudentAddresses().clear();
            existingStudent.getStudentAddresses().addAll(updateStudent.getStudentAddresses());
            existingStudent.getStudentAddresses().forEach(studentAddress -> studentAddress.setStudent(existingStudent));
        }

        return studentRepository.save(existingStudent);
    }


    @Transactional
    public void deleteStudent(Long studentId) {
        System.out.println("Attempting to delete student with ID: " + studentId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Delete Student :" + studentId));

        //System.out.println("Deleting student address for student ID: " + studentId);
        studentAddressRepository.deleteByStudent(student);

        // System.out.println("Deleting student with ID: " + studentId);
        studentRepository.delete(student);
        //System.out.println("Deleting student with ID: " + studentId);
    }
}
