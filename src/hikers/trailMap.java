package hikers;

import java.util.HashMap;
import java.util.TreeMap;

import hikingHistory.hikingHistory;

public class trailMap {

	
	
	private HashMap< String,Trails> hikingMags;
	
	public trailMap(HashMap <String, Trails> hikingMaps) {
		
		this.hikingMags=hikingMaps;

		
	}

	public HashMap<String, Trails> getHikingMags() {
		return hikingMags;
	}

	public void setHikingMags(HashMap<String, Trails> hikingMags) {
		this.hikingMags = hikingMags;
	}

	@Override
	public String toString() {
		return "HikingMap [HikingMags=" + hikingMags + "]";
	}
	
	
	
	
}
