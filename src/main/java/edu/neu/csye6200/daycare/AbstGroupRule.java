package edu.neu.csye6200.daycare;

import java.util.*;

public abstract class AbstGroupRule {

	public abstract void assign(Student s, Teacher t);
	public abstract Map<Integer,List<Group>> generateGroups(Teachers ts, Students ss) throws Group.BadGroupSizeException;

}
