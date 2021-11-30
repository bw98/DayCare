package edu.neu.csye6200.daycare;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Students extends AbstStudents{
	private List<Person> ss;
	
	public Students() {
		ss = new ArrayList<>();
	}
	
	public Students(Student[] ss) {
		this.ss = Arrays.asList(ss);
	}
	
	public Students(List<Person> ss) {
		this.ss = ss;
	}
	
	/**
	 * Create a Students object from a CSV file. 
	 * @param csvFile Put the file name here!!!
	 */
	public Students(String csvFile) {
		this.ss = Students.parseStudents(csvFile).toList();
		
	}
	
	public static Students parseStudents(String csvFile) {
		FileReader fr = null;
		try {
			fr = new FileReader(csvFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Please check your csv file!");
			return null;
		} 
		FileUtil reader = new FileUtil(fr);
		Students ss = new Students();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				ss.add(Student.parseStudentFromString(Arrays.asList(line.split(","))));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("File error. Line is: " + line);
			
		}
		
		try {
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ss;
		
	}
	
	public static List<Person> asList(Students ss){
		return ss.toList();
	}
	
	public List<Person> toList(){
		return this.getStudents();
	}
	
	public void add(Student t) {
		ss.add(t);
	}
	
	public void remove(Student t) {
		try {
			ss.remove(t);
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.err.println("Student not found! Disgard removing:" + t);
		}
	}
	
	public List<Person> getStudents(){
		return ss;
	}


	public Iterator<Person> iterator() {
		return ss.iterator();
	}
	
	@Override 
	public String toString() {
		return ss.toString();
	}
}
