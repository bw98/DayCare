package edu.neu.csye6200.daycare;

import java.util.*;

import edu.neu.csye6200.daycare.Group.BadGroupSizeException; 

public class ClassRoom extends AbstClassRoom{
	private int capacity = 0;
	private int size = 0;
	private int cid = 0;
	private int level = 0;
	private List<Group> groups = new ArrayList<>();
	
	public ClassRoom() {
		
	}
	
	public ClassRoom(int id, int capacity) {
		this.setCid(id);
		this.setCapacity(capacity);
		this.setSize(1);
	}
	
	public ClassRoom(List<Group> groups, int id, int level) {
		this.setCapacity(groups.size());
		this.setGroups(groups);
		this.setCid(id);
		this.setSize(1);

	}
	
	public ClassRoom(Group g, int id, int capacity) {
		groups.add(g);
		this.setCapacity(capacity);
		this.setLevel(g.getCapacity());
		this.setCid(id);
		this.setSize(1);

	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public int addGroup(Group g) {
		if(this.getCapacity() > this.getSize()) {
//			this.setCapacity(this.getCapacity() + 1);
			this.getGroups().add(g);
			this.setSize(this.getSize()+1);
			return 0;
		}
		else {
			return -1;
		}
	}
	
	@Override
	public String toString() {
		return "Class room id: "+ this.getCid() + " Capacity: " + this.getCapacity() + " Level: "+ this.getLevel() + " Current size: " + this.getSize();
	}
}
