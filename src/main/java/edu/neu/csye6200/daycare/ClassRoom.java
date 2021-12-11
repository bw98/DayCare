package edu.neu.csye6200.daycare;

import java.util.List; 

public class ClassRoom {
	private int capacity = 0;
	private int cid = 0;
	private List<Group> groups = null;
	
	public ClassRoom() {
		
	}
	
	public ClassRoom(int id) {
		this.setCid(id);
	}
	
	public ClassRoom(List<Group> groups, int id) {
		this.setCapacity(groups.size());
		this.setGroups(groups);
		this.setCid(id);
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
}
