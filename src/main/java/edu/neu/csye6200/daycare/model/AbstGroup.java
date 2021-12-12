package edu.neu.csye6200.daycare.model;

import edu.neu.csye6200.daycare.model.Group.BadGroupSizeException;
import edu.neu.csye6200.daycare.model.Student;

public abstract class AbstGroup {
	public abstract void addStudent(Student s) throws BadGroupSizeException;
	public abstract int getCurGroupSize();
}
