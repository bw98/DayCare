package edu.neu.csye6200.daycare;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class TeacherFactory extends AbstractPersonFactory{
	

	@Override
	public Person getObject(String line) {
		// TODO Auto-generated method stub
		List<String> list=Arrays.asList(line.split(","));
		return Teacher.parseTeacherFromString(list);
	}
	
	/**
	 * 
	 * @param teacherId
	 * @param age
	 * @param credit
	 * @param registerTime
	 * @param firstName
	 * @param lastName
	 * @param classRoomNum	//Initially set to -1
	 * @param groupNum		//Initially set to -1
	 * @param phone
	 * @param address
	 * @return
	 */
	public Person getObject(int teacherId, int age, int credit, Date registerTime, String firstName, String lastName, int classRoomNum, int groupNum, String phone, String address) {
		return new Teacher(teacherId, age, credit, registerTime, firstName, lastName, -1, -1, phone, address);
	}

}