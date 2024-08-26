package com.StudentTeacherAPI.Repositories;

import com.StudentTeacherAPI.model.Student;
import com.StudentTeacherAPI.model.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.StudentTeacherAPI.model.StudentTeacher;

@Repository
public interface StudentTeacherRepository extends JpaRepository<StudentTeacher, Long>,CustomStudentTeacherRepository {
    @Transactional
    void deleteByStudent(Student student);

    @Transactional
    void deleteByTeacher(Teacher teacher);
}
