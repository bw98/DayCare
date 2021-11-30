package edu.neu.csye6200.daycare;

import java.util.Date;

public class Person {
	private int age;
	private String firstName;
	private String lastName;
	private Date registerTime;
	
	public Person(int age, String firstName, String lastName, Date registerTime) {
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
		this.setRegisterTime(registerTime);
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
	
	public static int compareByAge(Person a, Person b) {
		return Integer.compare(a.getAge(), b.getAge());
	}
	
	public static int compareByLastName(Person a, Person b) {
		return a.getLastName().compareToIgnoreCase(b.getLastName());
	}
	
	@Override
	public String toString() {
		return "Person: " + firstName + " " + lastName + ", age: " + age;
	}

}
