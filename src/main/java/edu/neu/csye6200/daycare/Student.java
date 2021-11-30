package edu.neu.csye6200.daycare;

import java.util.Date;
import java.util.List;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Student extends Person implements Comparable<Student> {
	private String parentFirstName;
	private String parentLastName;
	private String phone;
	private String address;
	private double gpa;
	
	public Student(int age, double gpa, Date registerTime, String firstName, String lastName, String parentFirstName, String parentLastName, String phone, String address) {
		super(age, firstName, lastName, registerTime);
		this.setParentFirstName(parentFirstName);
		this.setParentLastName(parentLastName);
		this.phone = phone;
		this.address = address;
		this.gpa = gpa;
	}
	
	public static Student parseStudentFromString(List<String> tokens) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		int age = -1;
		double gpa = 0;
		
		try {
			age = Integer.parseInt(tokens.get(0));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		try {
			gpa = Double.parseDouble(tokens.get(1));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		try {
			date = df.parse(tokens.get(2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String lastName = tokens.get(3);
		String firstName = tokens.get(4);
		String parentFirstName = tokens.get(5);
		String parentLastName = tokens.get(6);
		String phone = tokens.get(7);
		String address = tokens.get(8);
		
		return new Student(age, gpa, date, lastName, firstName, parentLastName, parentFirstName, phone, address);
	}
	
	public static String serialize(Student s) {
		StringBuffer sBuffer = new StringBuffer();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		sBuffer.append(s.getAge());
		sBuffer.append(',');
		sBuffer.append(s.getGpa());
		sBuffer.append(',');
		sBuffer.append(df.format(s.getRegisterTime()));
		sBuffer.append(',');
		sBuffer.append(s.getLastName());
		sBuffer.append(',');
		sBuffer.append(s.getFirstName());
		sBuffer.append(',');
		sBuffer.append(s.getParentLastName());
		sBuffer.append(',');
		sBuffer.append(s.getParentFirstName());
		sBuffer.append(',');
		sBuffer.append(s.getPhone());
		sBuffer.append(',');
		sBuffer.append(s.getAddress());
		return sBuffer.toString();
	}
	
	@Override
	public int compareTo(Student o) {
		return Double.compare(this.getGpa(), o.getGpa());
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public String getParentFirstName() {
		return parentFirstName;
	}

	public void setParentFirstName(String parentFirstName) {
		this.parentFirstName = parentFirstName;
	}

	public String getParentLastName() {
		return parentLastName;
	}

	public void setParentLastName(String parentLastName) {
		this.parentLastName = parentLastName;
	}
	
	@Override
	public String toString() {
		String reString = super.toString();
		reString += ", is a student having a GPA of: " + gpa;
		return reString;
	}
	
}
