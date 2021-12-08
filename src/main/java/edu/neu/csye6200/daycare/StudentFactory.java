package edu.neu.csye6200.daycare;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class StudentFactory extends AbstractPersonFactory{

	@Override
	public Person getObject(String line) {
		// TODO Auto-generated method stub
		List<String> list=Arrays.asList(line.split(","));
		return Student.parseStudentFromString(list);
	}
	
	public Person getObject(int studentId, int age, double gpa, Date registerDate, Date renewDate, String firstName, String lastName, String parentFirstName, String parentLastName, String phone, String address) {
		return new Student(studentId, age, gpa, registerDate, renewDate, lastName, firstName, parentLastName, parentFirstName, phone, address);
	}
	
	
}