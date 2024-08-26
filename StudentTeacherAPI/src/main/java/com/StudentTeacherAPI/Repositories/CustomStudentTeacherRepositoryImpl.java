package com.StudentTeacherAPI.Repositories;


import com.StudentTeacherAPI.DTO.StudentTeacherDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class CustomStudentTeacherRepositoryImpl implements CustomStudentTeacherRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<StudentTeacherDTO> findStudentTeacherDetails() {
        String queryStr = "SELECT new com.StudentTeacherAPI.DTO.StudentTeacherDTO(s.studentId, t.teacherId, s.firstName, t.firstName) " +
                "FROM StudentTeacher st " +
                "JOIN st.student s " +
                "JOIN st.teacher t";

        TypedQuery<StudentTeacherDTO> query = entityManager.createQuery(queryStr, StudentTeacherDTO.class);
        return query.getResultList();
    }
}
