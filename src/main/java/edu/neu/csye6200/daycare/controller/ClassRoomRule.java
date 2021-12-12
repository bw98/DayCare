package edu.neu.csye6200.daycare.controller;

import edu.neu.csye6200.daycare.model.ClassRoom;
import edu.neu.csye6200.daycare.model.Group;

import java.util.*;
import java.util.Map.Entry;

public class ClassRoomRule extends AbstClassRule {
	private int cid = 0;
	private Map<Integer, ClassRoom> rooms = new HashMap<>();		// room id, room
	private Map<Integer, List<Integer>> roomLevels = new HashMap<>();	//level, room ids

	private AbstClassRoomFactory cf = ClassRoomFactory.getInstance();
	
	public ClassRoomRule() {}
	
	public ClassRoomRule(List<Group> gl) {
		int _level = 0;
		for (Group g: gl) {
			_level = g.getCapacity();
			if(!roomLevels.containsKey(_level)) {
				this.createClassRoom(g);
//				System.out.println(this.getClassRoom(newid));
			}else {
				ClassRoom avaliable = this.getAvaliableRoom(_level);
//				System.out.println("Avaliable room: " + avaliable);
				if(avaliable == null) {
					this.createClassRoom(g);
//					System.out.println(this.getClassRoom(newid));
				}else {
					this.assignGroup(g, avaliable);
				}
			}
		}
	}
	
	public ClassRoomRule(GroupRule gr) {
		Map<Integer, Group> groups = gr.getGroups();
		int _level = 0;
		for (Entry<Integer, Group> g: groups.entrySet()) {
			_level = g.getValue().getCapacity();
			if(!roomLevels.containsKey(_level)) {
				this.createClassRoom(g.getValue());
			}else {
				ClassRoom avaliable = this.getAvaliableRoom(_level);
				if(avaliable == null) {
					this.createClassRoom(g.getValue());
				}else {
					this.assignGroup(g.getValue(), avaliable);
				}
			}
		}
	}
	
	private ClassRoom getAvaliableRoom(int _level) {
		if(!this.roomLevels.containsKey(_level)) {
			return null;
		}
		ClassRoom f = null;
		for(int _cid :this.roomLevels.get(_level)) {
			ClassRoom curr_room = this.getClassRoom(_cid);
			if(curr_room.getSize() < this.lv2cap(_level)) {
				f = curr_room;
				break;
			}
		}
		return f;
	}
	
	
	private void createNewLevel(int _level) {
		this.roomLevels.put(_level, new ArrayList<Integer>());
	}
	
	private void classLevelAddCid(int _level, int _cid) {
		if(!this.roomLevels.containsKey(_level)) {
			this.createNewLevel(_level);;
		}
		this.roomLevels.get(_level).add(_cid);
	}

	private int cidUp() {
		this.setCid(cid+1);
		return this.getCid();
	}
	
	private int createClassRoom(Group g) {
		int _cid = this.cidUp();
		int _level =g.getCapacity();
//		System.out.println("cid: " + this.getCid() +" Group level: " + _level + " capacity: " + this.lv2cap(_level));
		ClassRoom new_room = cf.getObject(g, _cid, this.lv2cap(_level));
//		System.out.println(new_room);
		rooms.put(_cid, new_room);
		if(!this.getClassLevel().containsKey(_level)) {
			this.createNewLevel(_level);
		}
		this.classLevelAddCid(_level, _cid);
		return _cid;
	}
	
	@Override
	public ClassRoom getClassRoom(int _cid) {
		return this.getClassrooms().get(_cid);
	}
	
	@Override
	public int assignGroup(Group g, ClassRoom c) {
		return c.addGroup(g);
	}
	
	@Override
	public int assignGroup(Group g, int cid) {
		return this.assignGroup(g, this.getClassRoom(cid));
	}
	
	@Override 
	public int assignGroup(Group g) {
		ClassRoom a = this.getAvaliableRoom(g.getCapacity());
		if (a == null) {
			this.createClassRoom(g);
		}
		else {
			assignGroup(g,a);
		}
		return 0;
	}
	
	@Override
	public int assignGroups(List<Group> gs) {
		if(gs == null) {
			return -1;
		}
		ClassRoomRule new_rule = new ClassRoomRule(gs);
		this.setClassLevel(new_rule.getClassLevel());
		this.setClassRooms(new_rule.getClassrooms());
		return 0;
	}


	public static ClassRoomRule generateClassRooms(GroupRule groups) {
		return new ClassRoomRule(groups);
	}

	private int getCid() {
		return cid;
	}

	private void setCid(int cid) {
		this.cid = cid;
	}

	@Override
	public Map<Integer, ClassRoom> getClassrooms() {
		return rooms;
	}
	
	@Override
	public void setClassRooms(Map<Integer, ClassRoom> c) {
		this.rooms = c;
	}

	private Map<Integer, List<Integer>> getClassLevel() {
		return roomLevels;
	}

	private void setClassLevel(Map<Integer, List<Integer>> classLevel) {
		this.roomLevels = classLevel;
	}

	
	private int lv2cap(int groupSize) {
		int max = 0;
		switch (groupSize) {
			case 4: max = 3;
					break;
			case 5: max = 3;
					break;
			case 6: max = 3;
					break;
			case 8: max = 3;
					break;
			case 12: max = 2;
					break;
			case 15: max = 2;
					break;
			default: break;
		}
		return max;
	}

	@Override
	public String toString() {
		String result = "";
		for(Entry<Integer, ClassRoom> c: this.getClassrooms().entrySet()) {
			result = result + c.getValue() + "\n";
		}
		return result;
//		return this.getClassrooms().toString();
	}
	
	public static void demo() {
		Group g1 = new Group(4);
		Group g2 = new Group(5);
		Group g3 = new Group(6);
		Group g4 = new Group(8);
		Group g5 = new Group(12);
		Group g6 = new Group(15);
		Group g7 = new Group(12);
		Group g8 = new Group(15);
		Group g9 = new Group(15);
		Group g10 = new Group(15);
		Group g11 = new Group(15);

		List<Group> gs = Arrays.asList(g1,g2,g3,g4,g5,g6,g7,g8,g9,g10);
		ClassRoomRule r = new ClassRoomRule(gs);
		// System.out.println(r + "\n");
		r.assignGroup(g11);
//		for (Entry<Integer, ClassRoom> e:r.getClassrooms().entrySet()) {
//			System.out.println(e.getValue());
//		}
		// System.out.println(r);
	}
}
