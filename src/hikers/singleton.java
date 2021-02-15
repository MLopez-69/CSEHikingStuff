package hikers;

import java.util.HashMap;
import java.util.TreeMap;

public class singleton {
	public TreeMap<String, User> users;
	public HashMap<String, Trails> trails;
	public TreeMap<String,hikingHistory> history;
	public static singleton data=new singleton();
	
	private singleton() {
		users=new TreeMap<String, User>();
		trails=new HashMap<String, Trails>(500000);
		history=new TreeMap<String,hikingHistory>();
	}
	
	public static singleton getInstance() {
		return data;
	}
	
	protected Object readResolve() {
		return data;
	}

	public TreeMap<String, User> getUsers() {
		return users;
	}

	public void setUsers(TreeMap<String, User> users) {
		this.users = users;
	}

	public HashMap<String, Trails> getTrails() {
		return trails;
	}

	public void setTrails(HashMap<String, Trails> trails) {
		this.trails = trails;
	}

	public TreeMap<String, hikingHistory> getHistory() {
		return history;
	}

	public void setHistory(TreeMap<String, hikingHistory> history) {
		this.history = history;
	}
}
