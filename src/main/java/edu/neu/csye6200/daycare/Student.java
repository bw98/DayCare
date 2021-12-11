package edu.neu.csye6200.daycare;

import edu.neu.csye6200.daycare.vaccineInfo.Vaccine;
import edu.neu.csye6200.daycare.vaccineInfo.VaccineFactory;
import edu.neu.csye6200.daycare.vaccineInfo.VaccineUtil;

import java.util.Date;
import java.util.List;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;


public class Student extends Person implements Comparable<Student> {
	private String parentFirstName;
	private String parentLastName;
	private String phone;
	private String address;
	private int studentId;
	private double gpa;
	private ConcurrentHashMap<Vaccine, Vector<Integer>> immuRecord;
	public ConcurrentHashMap<Vaccine, Vector<Integer>> getImmuRecord(){
		return immuRecord;
	}
	public void setImmuRecord(){
		List<String> record = VaccineUtil.getVaccineList(this.studentId);
		if(record.isEmpty()){
			System.out.println("record empty");
			return;
		}
		int l = 1, r = record.size() - 1;
		if((r - l) % 2 == 0){
			System.out.println("err");
			return;
		}
		for(int i = l; i <= r; i+= 2){
			Vaccine temp = VaccineFactory.getVaccine(record.get(i));
			if(temp == null){
				System.out.println("Student init vaccine err!");
				continue;
			}
			immuRecord.putIfAbsent(temp, new Vector<>());
			immuRecord.get(temp).add(Integer.valueOf(record.get(i + 1)));
		}
	}

	public void initRecord(){
		VaccineFactory.getInstance();
		for(Vaccine v : VaccineFactory.getObject()){
			this.immuRecord.put(v, new Vector<>());
		}
	}
	public Student(int studentId, int age, double gpa, Date registerDate, Date renewDate, String firstName, String lastName, String parentFirstName, String parentLastName, String phone, String address) {
		super(age, firstName, lastName, registerDate, renewDate);
		this.setParentFirstName(parentFirstName);
		this.setParentLastName(parentLastName);
		this.studentId = studentId;
		this.phone = phone;
		this.address = address;
		this.gpa = gpa;
		immuRecord = new ConcurrentHashMap<>();
		initRecord();
		setImmuRecord();
	}
	
	public static Student parseStudentFromString(List<String> tokens) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		int id = -1;
		Date registerDate = new Date();
		Date renewDate = new Date(0);
		int age = -1;
		double gpa = 0;
		System.out.println(tokens);
		try {
			id = Integer.parseInt(tokens.get(0));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		try {
			age = Integer.parseInt(tokens.get(1));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		try {
			gpa = Double.parseDouble(tokens.get(2));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(tokens.get(3));
			registerDate=df.parse(tokens.get(3));
			
			System.out.println(registerDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(tokens.get(4)=="") {
			renewDate=null;
		}
		else {
			try {
				renewDate = df.parse(tokens.get(4));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		String lastName = tokens.get(5);
		String firstName = tokens.get(6);
		String parentFirstName = tokens.get(7);
		String parentLastName = tokens.get(8);
		String phone = tokens.get(9);
		String address = tokens.get(10);
		
		return new Student(id, age, gpa, registerDate, renewDate, lastName, firstName, parentLastName, parentFirstName, phone, address);
	}
	
	public static String serialize(Student s) {
		StringBuffer sBuffer = new StringBuffer();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		sBuffer.append(s.getStudentId());
		sBuffer.append(',');
		sBuffer.append(s.getAge());
		sBuffer.append(',');
		sBuffer.append(s.getGpa());
		sBuffer.append(',');
		sBuffer.append(df.format(s.getRegisterTime()));
		sBuffer.append(',');
		if(s.getRenewDate()==null) {
			sBuffer.append("");
		}
		else {
			sBuffer.append(df.format(s.getRenewDate()));
		}
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

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	@Override
	public String toString() {
		String reString = super.toString();
		reString += ", is a student having a GPA of: " + gpa;
		return reString;
	}
}
