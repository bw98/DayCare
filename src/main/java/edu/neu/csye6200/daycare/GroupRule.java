package edu.neu.csye6200.daycare;

import java.util.*;

import edu.neu.csye6200.daycare.Group.BadGroupSizeException;

public class GroupRule extends AbstGroupRule{

	private Map<Integer, List<Group>> groupMap;
	private Map<Integer, Group> groups;

	public Map<Integer, List<Group>> getGroupMap() {
		return groupMap;
	}


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

	public Map<Integer, List<Group>> generateGroups(Teachers ts, Students ss) throws BadGroupSizeException {
		groupMap = new HashMap<>();
		List<Person> studentsList = ss.getStudents();
		List<Person> teachersList = ts.getTeachers();
        int tlIndex = 0;
        int groupId = 1;
		for (Person student : studentsList) {
			if (student.getAge() >= 6 && student.getAge() <= 12) {
				if (!groupMap.containsKey(4)) {
					groupMap.put(4, new ArrayList<>());
				}
				List<Group> groupList = groupMap.get(4);
				if (groupList.size() == 0 || groupList.get(groupList.size() - 1).getCurGroupSize() == 4) {
					Group group = new Group((Teacher)teachersList.get(tlIndex), 4, groupId);
					tlIndex++;
					groupId++;
					group.addStudent((Student)student);
					groupList.add(group);
				} else {
					groupList.get(groupList.size() - 1).addStudent((Student)student);
				}
			} else if (student.getAge() >= 13 && student.getAge() <= 24) {
				if (!groupMap.containsKey(5)) {
					groupMap.put(5, new ArrayList<>());
				}
				List<Group> groupList = groupMap.get(5);
				if (groupList.size() == 0 || groupList.get(groupList.size() - 1).getCurGroupSize() == 5) {
					Group group = new Group((Teacher)teachersList.get(tlIndex), 5, groupId);
					tlIndex++;
					groupId++;
					group.addStudent((Student)student);
					groupList.add(group);

				} else {
					groupList.get(groupList.size() - 1).addStudent((Student)student);
				}
			} else if (student.getAge() >= 25 && student.getAge() <= 35) {
				if (!groupMap.containsKey(6)) {
					groupMap.put(6, new ArrayList<>());
				}
				List<Group> groupList = groupMap.get(6);
				if (groupList.size() == 0 || groupList.get(groupList.size() - 1).getCurGroupSize() == 6) {
					Group group = new Group((Teacher)teachersList.get(tlIndex), 6, groupId);
					tlIndex++;
					groupId++;
					group.addStudent((Student)student);
					groupList.add(group);
				} else {
					groupList.get(groupList.size() - 1).addStudent((Student)student);
				}
			} else if (student.getAge() >= 63 && student.getAge() <= 47) {
				if (!groupMap.containsKey(8)) {
					groupMap.put(8, new ArrayList<>());
				}
				List<Group> groupList = groupMap.get(8);
				if (groupList.size() == 0 || groupList.get(groupList.size() - 1).getCurGroupSize() == 8) {
					Group group = new Group((Teacher)teachersList.get(tlIndex), 7, groupId);
					tlIndex++;
					groupId++;
					group.addStudent((Student)student);
					groupList.add(group);
				} else {
					groupList.get(groupList.size() - 1).addStudent((Student)student);
				}
			} else if (student.getAge() >= 48 && student.getAge() <= 59) {
				if (!groupMap.containsKey(12)) {
					groupMap.put(12, new ArrayList<>());
				}
				List<Group> groupList = groupMap.get(12);
				if (groupList.size() == 0 || groupList.get(groupList.size() - 1).getCurGroupSize() == 12) {
					Group group = new Group((Teacher)teachersList.get(tlIndex), 5, groupId);
					tlIndex++;
					groupId++;
					group.addStudent((Student)student);
					groupList.add(group);
				} else {
					groupList.get(groupList.size() - 1).addStudent((Student)student);
				}
			} else if (student.getAge() >= 60) {
				if (!groupMap.containsKey(15)) {
					groupMap.put(15, new ArrayList<>());
				}
				List<Group> groupList = groupMap.get(15);
				if (groupList.size() == 0 || groupList.get(groupList.size() - 1).getCurGroupSize() == 15) {
					Group group = new Group((Teacher)teachersList.get(tlIndex), 15, groupId);
					tlIndex++;
					groupId++;
					group.addStudent((Student)student);
					groupList.add(group);
				} else {
					groupList.get(groupList.size() - 1).addStudent((Student)student);
				}
			}
		}
		return groupMap;
	}

}
