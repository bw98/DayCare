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
	
	public Person getObject(int teacherId, int age, int credit, Date registerTime, String firstName, String lastName, int classRoomNum, int groupNum, String phone, String address) {
		return new Teacher(teacherId, age, credit, registerTime, firstName, lastName, classRoomNum, groupNum, phone, address);
	}

}