package edu.neu.csye6200.daycare.controller;

import edu.neu.csye6200.daycare.model.*;

import java.util.*;

public abstract class AbstGroupRule {

	public abstract void assign(Student s, Teacher t);
	public abstract Map<Integer,List<Group>> generateGroups(Teachers ts, Students ss) throws Group.BadGroupSizeException;

}
