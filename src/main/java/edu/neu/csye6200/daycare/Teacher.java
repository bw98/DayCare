package edu.neu.csye6200.daycare;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Teacher extends Person implements Comparable<Teacher>{

	private String phone;
	private String address;
	private int teacherId;
	private int credits;
	private boolean isAssigned;
	private int classRoomNum;
	private int groupNum;
	
	public Teacher(int teacherId, int age, int credit, Date registerTime, String firstName, String lastName, int classRoomNum, int groupNum, String phone, String address) {
		super(age, firstName, lastName, registerTime);
		this.teacherId = teacherId;
		this.phone = phone;
		this.credits = credit;
		this.classRoomNum = classRoomNum;
		this.groupNum = groupNum;
		this.address = address;
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

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public boolean isAssigned() {
		return isAssigned;
	}

	public void setAssigned(boolean isAssigned) {
		this.isAssigned = isAssigned;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getClassRoomNum() {
		return classRoomNum;
	}

	public void setClassRoomNum(int classRoomNum) {
		this.classRoomNum = classRoomNum;
	}

	public int getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

	@Override
	public int compareTo(Teacher o) {

		return Integer.compare(this.getCredits(), o.getCredits());
	}
	
	public static Teacher parseTeacherFromString(List<String> tokens) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int id = -1;
		Date date = new Date();
		int age = -1;
		int credits = 0;
		int classRoomNum =-1;
		int groupNum = -1;
		
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
			credits = Integer.parseInt(tokens.get(2));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		try {
			date = df.parse(tokens.get(3));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String lastName = tokens.get(4);
		String firstName = tokens.get(5);
		
		try {
			classRoomNum = Integer.parseInt(tokens.get(6));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		try {
			groupNum = Integer.parseInt(tokens.get(2));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		String phone = tokens.get(8);
		String address = tokens.get(9);
		
		return new Teacher(id, age, credits, date, lastName, firstName, classRoomNum, groupNum, phone, address);
	}
	
	public static String serialize(Teacher t) {
		StringBuffer sBuffer = new StringBuffer();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		sBuffer.append(t.getTeacherId());
		sBuffer.append(',');
		sBuffer.append(t.getAge());
		sBuffer.append(',');
		sBuffer.append(t.getCredits());
		sBuffer.append(',');
		sBuffer.append(df.format(t.getRegisterTime()));
		sBuffer.append(',');
		sBuffer.append(t.getLastName());
		sBuffer.append(',');
		sBuffer.append(t.getFirstName());
		sBuffer.append(',');
		sBuffer.append(t.getPhone());
		sBuffer.append(',');
		sBuffer.append(t.getAddress());
		sBuffer.append(',');
		sBuffer.append(t.isAssigned());
		sBuffer.append(',');
		sBuffer.append(t.getClassRoomNum());
		sBuffer.append(',');
		sBuffer.append(t.getGroupNum());

		return sBuffer.toString();
	}


	@Override
	public String toString() {
		String reString = super.toString();
		reString += ", is a teacher having a credits of: " + this.getCredits();
		return reString;
	}
}
