package edu.neu.csye6200.daycare;

import java.util.List;

public class ClassRoomFactory extends AbstClassRoomFactory{
	private static ClassRoomFactory instance = new ClassRoomFactory();
	
	public static ClassRoomFactory getInstance() {
		return instance;
	}
	
	public ClassRoom getObject() {
		return new ClassRoom();
	}
	
	public ClassRoom getObject(List<Group> groups, int id, int level) {
		return new ClassRoom(groups, id, level);
	}

	@Override
	public ClassRoom getObject(int id, int capacity) {
		return new ClassRoom(id, capacity);
	}

	@Override
	public ClassRoom getObject(Group g, int id, int capacity) {
		return new ClassRoom(g, id, capacity);
	}
}
