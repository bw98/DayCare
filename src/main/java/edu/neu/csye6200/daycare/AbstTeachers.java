package edu.neu.csye6200.daycare;

import java.util.*;

public abstract class AbstTeachers implements Iterable<Person>{

//	public abstract Teachers parseTeachers(String csvFile);
//	public abstract List<Person> asList(Teachers ts);
	public abstract List<Person> toList();
	public abstract void add(Teacher t) ;
	public abstract void remove(Teacher t);
	protected abstract List<Person> getTeachers();

	@Override
	public abstract Iterator<Person> iterator();
}
