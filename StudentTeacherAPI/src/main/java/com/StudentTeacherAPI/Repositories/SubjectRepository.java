package com.StudentTeacherAPI.Repositories;

import com.StudentTeacherAPI.model.Subject;
import com.StudentTeacherAPI.model.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Transactional
    void deleteByTeacher(Teacher teacher);
}

