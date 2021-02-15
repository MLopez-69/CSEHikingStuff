package hikingHistory;

import java.util.TreeMap;

public class hikingMap{
	
	//javafx build path below
	//--module-path C:\Users\javafx-sdk-14.0.2.1\lib --add-modules javafx.controls,javafx.fxml
	
	
	
	private TreeMap< String,hikingHistory> hikingMags;
	
	public hikingMap(TreeMap <String, hikingHistory> hikingMaps) {
		
		this.hikingMags=hikingMaps;

		
	}

	public TreeMap<String, hikingHistory> getHikingMags() {
		return hikingMags;
	}

	public void setHikingMags(TreeMap<String, hikingHistory> hikingMags) {
		this.hikingMags = hikingMags;
	}

	@Override
	public String toString() {
		return "UserMap [userMags=" + hikingMags + "]";
	}
	
	
	
	
	
	
}
