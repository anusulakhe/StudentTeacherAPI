package com.StudentTeacherAPI.DTO;

public class StudentTeacherDTO {
    private Long studentId;
    private Long teacherId;
    private String studentFirstName;
    private String teacherFirstName;


    public StudentTeacherDTO(Long studentId, Long teacherId, String studentFirstName, String teacherFirstName) {
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.studentFirstName = studentFirstName;
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }


}
