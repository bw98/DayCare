package edu.neu.csye6200.daycare;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
//		FileReader fr = null;
//		try {
//			fr = new FileReader(csvFile);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			System.err.println("Please check your csv file!");
//			return null;
//		}
//		FileUtil reader = new FileUtil(fr);
//		Teachers ts = new Teachers();
//		String line = null;
//		try {
//			while ((line = reader.readLine()) != null) {
//				ts.add(Teacher.parseTeacherFromString(Arrays.asList(line.split(","))));
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.err.println("File error. Line is: " + line);
//
//		}
//
//		try {
//			reader.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ts;

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
		Teachers ts = new Teachers();
		String[] arrayOfString = CSVString.split("\n");
		for (String string : arrayOfString) {
			List<String> list= Arrays.asList(string.split(","));
			ts.add(Teacher.parseTeacherFromString(list));
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

	public int getNumber() {
		return ts.size();
	}
	public Iterator<Person> iterator() {
		return ts.iterator();
	}
	
	@Override 
	public String toString() {
		return ts.toString();
	}
	
	public void update_renew_date(int id,String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for(Person s: this.ts) {
			if(s instanceof Teacher) {
				if(((Teacher)s).getTeacherId()==id) {
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
		for(Person s:ts) {
			if(s instanceof Teacher) {
				writer.write(Teacher.serialize((Teacher)s));
			}
		}
		writer.close();
		
	}
}
