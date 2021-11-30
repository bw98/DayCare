package edu.neu.csye6200.daycare;

import java.util.Date;

public class Person {
    private int id;
    private int age;
    private String firstName;
    private String lastName;
    private Date registerTime;

    public Person(int id, int age, String firstName, String lastName, Date registerTime) {
        this.id = id;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registerTime = registerTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}
