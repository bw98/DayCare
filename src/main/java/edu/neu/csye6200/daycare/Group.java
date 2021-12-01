package edu.neu.csye6200.daycare;

public class Group extends AbstGroup{
	public class BadGroupSizeException extends Exception{

		private static final long serialVersionUID = -1793651483822500495L;

		public BadGroupSizeException() {
			super();
		}
		
		public BadGroupSizeException(String msg) {
			super(msg);
		}
	}

	private int groupId = -1;
	private int size;
	private int capacity;
	private Teacher t;
	private Students ss;
	
	public Group(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.t = null;
		this.ss = null;
	}
	
	public Group(Teacher t, int capacity) {
		this.setTeacher(t);
		this.capacity = capacity;
		this.size = 0;
		this.ss = null;
	}
	
	public Group(Teacher t, int capacity, Student[] ss) {
		if(ss.length > capacity) {
			System.err.println("Students exceeds the group size!");
			this.t = null;
			this.capacity = -1;
			this.size = 0;
			this.ss = null;
			this.groupId = -1;
		}
		else {
			this.setTeacher(t);
			this.capacity = capacity;
			this.size = ss.length;
			this.ss = new Students(ss);
		}
		
	}
	
	@Override
	public void addStudent(Student s) throws BadGroupSizeException {
		if(this.capacity >= this.size) {
			throw new BadGroupSizeException("Group is full!");
		}
		else {
			this.ss.add(s);
			this.size++;
		}
	}

	@Override
	public int getCurGroupSize() {
		return this.capacity;
	}
	
	@Override
	public String toString() {
		return "This group: " + getGroupId() + ", has teacher: " + getTeacher() + " and " + getCurGroupSize() + " Students:\n" + getStudents();
	}

	public int getGroupId() {
		return groupId;
	}
	
	public void setGroupId(int id) {
		this.groupId = id;
	}
	
	public int size() {
		return this.size;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public Teacher getTeacher() {
		return this.t;
	}
	
	public void setTeacher(Teacher t) {
		this.t = t;
	}
	
	public Students getStudents() {
		return this.ss;
	}
	
	public void setStudents(Students ss) {
		this.ss = ss;
	}
}
