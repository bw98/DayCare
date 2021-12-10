package edu.neu.csye6200.daycare;

import java.util.*;

public abstract class AbstGroupRule {
	public abstract int assign(Student s, Teacher t);	// return 0 if success, else return -1.
	public abstract Map<Integer,List<Group>> generateGroups(Teachers ts, Students ss);
	
}
