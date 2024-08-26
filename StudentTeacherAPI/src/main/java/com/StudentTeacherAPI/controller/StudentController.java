package com.StudentTeacherAPI.controller;

import com.StudentTeacherAPI.DTO.StudentTeacherDTO;
import com.StudentTeacherAPI.model.Student;
import com.StudentTeacherAPI.service.StudentAddressService;
import com.StudentTeacherAPI.service.StudentService;
import com.StudentTeacherAPI.service.StudentTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;
    private final StudentTeacherService studentTeacherService;
    private final StudentAddressService studentAddressService;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);




    @Autowired
    public StudentController(StudentService studentService, StudentTeacherService studentTeacherService, StudentAddressService studentAddressService) {
        this.studentService = studentService;
        this.studentTeacherService = studentTeacherService;
        this.studentAddressService = studentAddressService;
    }

    @GetMapping("/getStudents")
    public ResponseEntity<List<Student>> getStudents() {
        logger.debug("Starting");
        logger.trace("GetStudent Method called");
        List<Student> students = studentService.getStudent();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/studentteacherdetails")
    public List<StudentTeacherDTO> getStudentTeacherDetails() {

        return studentTeacherService.getStudentTeacherDetails();
    }

    @GetMapping("/hi")
    public String hellohi() {
        logger.debug("Starting");
        logger.trace("Hi Method called");
        return "Hello Hi";
    }

    @PostMapping("/addstudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        try {
            Student savedStudent = studentService.saveStudent(student);
            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/mapStudentTeacher/{studentId}/{teacherId}")
    public ResponseEntity<Boolean> mapStudentTeacher(@PathVariable Long studentId, @PathVariable Long teacherId) {
        boolean isMapped = studentTeacherService.MapStudentTeacher(studentId, teacherId);
        return ResponseEntity.ok(isMapped);
    }

    @PutMapping("/student/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(studentId, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}
