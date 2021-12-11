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
	
	public ClassRoom getObject(List<Group> groups, int id) {
		return new ClassRoom(groups, id);
	}

	@Override
	public ClassRoom getObject(int id) {
		return new ClassRoom(id);
	}
}
