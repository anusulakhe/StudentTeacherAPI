package com.StudentTeacherAPI.Repositories;

import com.StudentTeacherAPI.model.Address;
import com.StudentTeacherAPI.model.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Transactional
    void deleteByTeacher(Teacher teacher);
}
