package edu.neu.csye6200.daycare.controller;


import edu.neu.csye6200.daycare.model.Person;

public abstract class AbstractPersonFactory {
	public abstract Person getObject(String line);
}
