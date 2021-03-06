package edu.neu.csye6200.daycare.controller;

import edu.neu.csye6200.daycare.model.ClassRoom;
import edu.neu.csye6200.daycare.model.Group;

import java.util.List;
import java.util.Map;

public abstract class AbstClassRule {
	public abstract ClassRoom getClassRoom(int cid);
	public abstract int assignGroup(Group g, int cid);
	public abstract int assignGroup(Group g, ClassRoom c);
	public abstract int assignGroup(Group g);
	public abstract int assignGroups(List<Group> gs);
//	public abstract int generateClassRooms(AbstGroupRule groups);
	public abstract Map<Integer, ClassRoom> getClassrooms();
	public abstract void setClassRooms(Map<Integer, ClassRoom> c);
}
