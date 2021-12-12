package edu.neu.csye6200.daycare.model;

import edu.neu.csye6200.daycare.controller.FileUtil;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;



public class Students extends AbstStudents {
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

		String CSVString = "";
		String thisLine = null;

		try {
			FileReader fr = new FileReader(csvFile);
			BufferedReader br = new BufferedReader(fr);
			while((thisLine = br.readLine()) != null) {
				CSVString += thisLine + "\n";
			}
			br.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Students ss = new Students();
        String[] arrayOfString = CSVString.split("\n");
		for (String string : arrayOfString) {
			List<String> list= Arrays.asList(string.split(","));
			ss.add(Student.parseStudentFromString(list));
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
	public int getNumber() {
		return ss.size();
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
	
	public Person find_student_by_id(int id) {
		Iterator<Person> iter=this.iterator();
		Person p=null;
		while(iter.hasNext()) {
			p=iter.next();
			if(p instanceof Student) {
				if(((Student)p).getStudentId()==id) {
					return p;
				}
			}
		}
		return null;
	}
	
	public void update_renew_date(int id,String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for(Person s: this.ss) {
			if(s instanceof Student) {
				if(((Student)s).getStudentId()==id) {
					// System.out.println(df.parse(date));
					s.setRenewDate(df.parse(date));
					// System.out.println(s.getRenewDate());
				}
			}
		}
		
	}
	
	public void write_to_csv(String csvFile) throws Exception {
		FileWriter fw=null;
		try {
			fw=new FileWriter(csvFile);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Please check your csv file!");
			// TODO: handle exception
		}
		FileUtil writer=new FileUtil(fw);
		for(Person s:ss) {
			if(s instanceof Student) {
				writer.write(Student.serialize((Student)s));
			}
		}
		writer.close();
		
	}
}
