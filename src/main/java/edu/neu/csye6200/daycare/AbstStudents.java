package edu.neu.csye6200.daycare;

import java.util.List;

public abstract class AbstStudents implements Iterable<Person>{
	public abstract void add(Student s);
	public abstract void remove(Student s);
	protected abstract List<Person> getStudents();
	public abstract List<Person> toList();
}
