package edu.neu.csye6200.daycare;

import java.io.*;
import java.util.*;

public class Teachers extends AbstTeachers{
	private List<Person> ts;
	
	public Teachers() {
		ts = new ArrayList<>();
	}
	
	public Teachers(Teacher[] ts) {
		this.ts = Arrays.asList(ts);
	}
	
	public Teachers(List<Person> ts) {
		this.ts = ts;
	}
	
	/**
	 * Create a Teachers object from a CSV file. 
	 * @param csvFile Put the file name here!!!
	 */
	public Teachers(String csvFile) {
		this.ts = Teachers.parseTeachers(csvFile).toList();
		
	}
	
	public static Teachers parseTeachers(String csvFile) {
		FileReader fr = null;
		try {
			fr = new FileReader(csvFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Please check your csv file!");
			return null;
		} 
		FileUtil reader = new FileUtil(fr);
		Teachers ts = new Teachers();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				ts.add(Teacher.parseTeacherFromString(Arrays.asList(line.split(","))));
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
		return ts;
		
	}
	
	public static List<Person> asList(Teachers ts){
		return ts.toList();
	}
	
	public List<Person> toList(){
		return this.getTeachers();
	}
	
	public void add(Teacher t) {
		ts.add(t);
	}
	
	public void remove(Teacher t) {
		try {
			ts.remove(t);
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.err.println("Teacher not found! Disgard removing:" + t);
		}
	}
	
	public List<Person> getTeachers(){
		return ts;
	}


	public Iterator<Person> iterator() {
		return ts.iterator();
	}
	
	@Override 
	public String toString() {
		return ts.toString();
	}
}
