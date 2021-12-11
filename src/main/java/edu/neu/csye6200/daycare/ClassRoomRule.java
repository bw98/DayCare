package edu.neu.csye6200.daycare;

import java.util.*;

public class ClassRoomRule extends AbstClassRule{
	private int cid = 0;
	private Map<Integer, ClassRoom> c = new HashMap<>();
	private Map<Integer, Integer> classLevel = new HashMap<>();
	private AbstClassRoomFactory cf = ClassRoomFactory.getInstance();
	
	public ClassRoomRule() {}
	
	public ClassRoomRule(List<ClassRoom> cs) {}
	
	
	@Override
	public ClassRoom getClassRoom(int cid) {
		return this.getC().get(cid);
	}

	@Override
	public int assignGroup(Group g) {
		int capa = g.getCapacity();
		
		return 0;
	}
	
	@Override
	public int assignGroup(Group g, int cid) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int assignGroups(List<Group> gs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int generateClassRooms(AbstGroupRule groups) {
		// TODO Auto-generated method stub
		return 0;
	}

	private int getCid() {
		return cid;
	}

	private void setCid(int cid) {
		this.cid = cid;
	}

	private Map<Integer, ClassRoom> getC() {
		return c;
	}

	private void setC(Map<Integer, ClassRoom> c) {
		this.c = c;
	}

	private Map<Integer, Integer> getClassLevel() {
		return classLevel;
	}

	private void setClassLevel(Map<Integer, Integer> classLevel) {
		this.classLevel = classLevel;
	}

	
	
}
