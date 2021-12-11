package edu.neu.csye6200.daycare;

import java.util.*;

public abstract class AbstClassRoomFactory {
	public abstract ClassRoom getObject();
	public abstract ClassRoom getObject(List<Group> groups, int id);
	public abstract ClassRoom getObject(int id);

}
