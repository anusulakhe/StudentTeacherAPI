package com.StudentTeacherAPI.service;

import com.StudentTeacherAPI.Repositories.StudentTeacherRepository;
import com.StudentTeacherAPI.Repositories.SubjectRepository;
import com.StudentTeacherAPI.Repositories.TeacherRepository;
import com.StudentTeacherAPI.model.Address;
import com.StudentTeacherAPI.model.Student;
import com.StudentTeacherAPI.model.Subject;
import com.StudentTeacherAPI.model.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final StudentTeacherRepository studentTeacherRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, StudentTeacherRepository studentTeacherRepository, SubjectRepository subjectRepository) {
        this.teacherRepository = teacherRepository;
        this.studentTeacherRepository = studentTeacherRepository;
        this.subjectRepository = subjectRepository;
    }

    public Teacher saveteacher(Teacher teacher) {
        if (teacher.getSubjects() != null) {
            for (Subject subject : teacher.getSubjects()) {
                subject.setTeacher(teacher);
            }
        }
        if(teacher.getAddresses()!= null){
            for (Address address : teacher.getAddresses()){
                address.setTeacher(teacher);
            }
        }
        return teacherRepository.save(teacher);
    }

    public Teacher update_Teacher(Long teacherId, Teacher updateTeacher) {
        Teacher existingTeacher = teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found :" + teacherId));
        existingTeacher.setFirstName(updateTeacher.getFirstName());
        existingTeacher.setLastName(updateTeacher.getLastName());
        existingTeacher.setEmail(updateTeacher.getEmail());
        return teacherRepository.save(existingTeacher);
    }
    @Transactional
    public Teacher updateTeacher(Long teacherId, Teacher updateTeacher) {
        Teacher existingTeacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found: " + teacherId));

        existingTeacher.setFirstName(updateTeacher.getFirstName());
        existingTeacher.setLastName(updateTeacher.getLastName());
        existingTeacher.setEmail(updateTeacher.getEmail());

        // Handle subjects associations
        if (updateTeacher.getSubjects() != null) {
            existingTeacher.getSubjects().clear();
            existingTeacher.getSubjects().addAll(updateTeacher.getSubjects());
            // Ensure the bidirectional relationship is maintained, if applicable
            existingTeacher.getSubjects().forEach(subject -> subject.setTeacher(existingTeacher));
        }


        if (updateTeacher.getAddresses() != null) {
            existingTeacher.getAddresses().clear();
            existingTeacher.getAddresses().addAll(updateTeacher.getAddresses());
            existingTeacher.getAddresses().forEach(address -> address.setTeacher(existingTeacher));
        }

        return teacherRepository.save(existingTeacher);
    }






    @Transactional
    public void deleteTeacher(Long teacherid) {
        Teacher teacher = teacherRepository.findById(teacherid)
                .orElseThrow(() -> new RuntimeException("Teacher not found :" + teacherid));
        studentTeacherRepository.deleteByTeacher(teacher);
        teacherRepository.delete(teacher);

    }


}
