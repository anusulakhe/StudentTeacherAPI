package com.StudentTeacherAPI.Repositories;

import com.StudentTeacherAPI.model.Student;
import com.StudentTeacherAPI.model.StudentAddress;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAddressRepository  extends JpaRepository<StudentAddress, Integer>{
    @Transactional
    void deleteByStudent(Student student);
}
