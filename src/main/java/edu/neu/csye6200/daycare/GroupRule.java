package edu.neu.csye6200.daycare;

import java.util.*;

import edu.neu.csye6200.daycare.Group.BadGroupSizeException;

public class GroupRule extends AbstGroupRule{
	private Map<Integer, List<Group>> groupMap;	// group capacity, group list
	private Map<Integer, Group> groups;			// group gid, group
	private int gid_i = 0;
	
	@Override
	public int assign(Student s, Teacher t) {
		int gid = t.getGroupNum();
		Group g = null;
		if (gid == -1) {
			g = new Group(t, ++gid_i);
		}
		else {
			g = groups.get(gid);
		}
		try {
			g.addStudent(s);
		} catch (BadGroupSizeException e) {
			e.printStackTrace();
			System.err.println("Group full, student not added!");
			return -1;
		}
		return 0;
	}

	@Override
	public Map<Integer, List<Group>> generateGroups(Teachers ts, Students ss) {

		return null;
	}

}
