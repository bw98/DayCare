package edu.neu.csye6200.daycare;

import java.util.List; 

public class ClassRoom {
	private int capacity = 0;
	private List<Group> groups = null;
	
	public ClassRoom() {
		
	}
	
	public ClassRoom(List<Group> groups) {
		this.setCapacity(groups.size());
		this.setGroups(groups);
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
}
