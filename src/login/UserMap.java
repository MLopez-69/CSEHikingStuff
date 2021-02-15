package login;

import java.util.TreeMap;

public class UserMap{
	
	//javafx build path below
	//--module-path C:\Users\javafx-sdk-14.0.2.1\lib --add-modules javafx.controls,javafx.fxml
	
	
	
	private TreeMap< String,User> userMags;
	
	public UserMap(TreeMap <String, User> userMaps) {
		
		this.userMags=userMaps;

		
	}

	public TreeMap<String, User> getUserMags() {
		return userMags;
	}

	public void setUserMags(TreeMap<String, User> userMags) {
		this.userMags = userMags;
	}

	@Override
	public String toString() {
		return "UserMap [userMags=" + userMags + "]";
	}
	
	
	
	
	
	
}
