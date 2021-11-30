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

	private int groupId;
	private int size;
	private int capacity;
	private Teacher t;
	private Students ss;
	
	public Group(int capacity) {
		this.capacity = capacity;
	}
	
	public Group(Teacher t, int capacity) {
		this.setTeacher(t);
		this.capacity = capacity;
	}
	
	public Group(Teacher t, int capacity, Students ss) {
		this.setTeacher(t);
		this.capacity = capacity;
		try {
			
		}
	}
	
	@Override
	public void addStudent(Student s) throws BadGroupSizeException {
		if(this.capacity >= this.size) {
			throw new BadGroupSizeException("Group is full!");
		}
		else {
			this.ss.add(s);
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
