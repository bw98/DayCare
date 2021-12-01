package edu.neu.csye6200.daycare;

import java.util.*;

import edu.neu.csye6200.daycare.Group.BadGroupSizeException;

public class GroupRule extends AbstGroupRule{
	private Map<Integer, List<Group>> groupMap;
	private Map<Integer, Group> groups;
	
	@Override
	public void assign(Student s, Teacher t) {
		int gid = t.getGroupNum();
		Group g = groups.get(gid);
		try {
			g.addStudent(s);
		} catch (BadGroupSizeException e) {
			e.printStackTrace();
			System.err.println("Group full, student not added!");
		}
	}

	@Override
	public Map<Integer, List<Group>> generateGroups(Teachers ts, Students ss) {
		// TODO Auto-generated method stub
		return null;
	}

}
