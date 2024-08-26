package com.StudentTeacherAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long teacherId;
    private String firstName;
    private String lastName;
    private String email;

    @ManyToMany(mappedBy = "teachers")
    @JsonIgnore
    private Set<Student> students = new HashSet<>();


    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Address> address = new HashSet<>();


    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private Set<Subject> subject = new HashSet<>();



    public Set<Address> getAddresses() {
        return address;
    }

    public void setAddresses(Set<Address> addresses) {
        this.address = addresses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subject = subjects;
    }

    public Set<Subject> getSubjects() {
        return subject;
    }




}
