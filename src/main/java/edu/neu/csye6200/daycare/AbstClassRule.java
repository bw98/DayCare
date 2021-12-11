package edu.neu.csye6200.daycare;

import java.util.List;

public abstract class AbstClassRule {
//	public abstract List<Group> getGroups();
	public abstract ClassRoom getClassRoom(int cid);
	public abstract int assignGroup(Group g);
	public abstract int assignGroup(Group g, int cid);
	public abstract int assignGroups(List<Group> gs);
	public abstract int generateClassRooms(AbstGroupRule groups);
}
