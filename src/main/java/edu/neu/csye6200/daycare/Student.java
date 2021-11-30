package edu.neu.csye6200.daycare;

import java.util.Date;

public class Student extends Person {
    private double gpa;
    private int studentId;
    private String fatherName;
    private String motherName;

    public Student(int id, int age, String firstName, String lastName, Date registerTime, double gpa, int studentId, String fatherName, String motherName) {
        super(id, age, firstName, lastName, registerTime);
        this.gpa = gpa;
        this.studentId = studentId;
        this.fatherName = fatherName;
        this.motherName = motherName;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }
}

