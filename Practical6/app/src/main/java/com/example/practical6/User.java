package com.example.practical6;

public class User {
    private int id;
    private String name;
    private String email;
    private String enrollmentNumber;
    private String semester;
    private String department;

    public User(String name, String email, String enrollmentNumber, String semester, String department) {
        this.name = name;
        this.email = email;
        this.enrollmentNumber = enrollmentNumber;
        this.semester = semester;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
// Getters and setters for the new fields
}
