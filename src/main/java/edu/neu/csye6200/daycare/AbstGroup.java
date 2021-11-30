package edu.neu.csye6200.daycare;

import edu.neu.csye6200.daycare.Group.BadGroupSizeException;

public abstract class AbstGroup {
	public abstract void addStudent(Student s) throws BadGroupSizeException;
	public abstract int getCurGroupSize();
}
