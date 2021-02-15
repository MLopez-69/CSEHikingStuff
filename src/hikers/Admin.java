package hikers;

<<<<<<< HEAD
import java.io.Serializable;
import java.util.TreeMap;

public class Admin extends User implements Serializable{
=======
import java.util.TreeMap;

import login.User;
import hikingHistory.hikingHistory;

public class Admin extends User{
>>>>>>> b23556ec6f7f2a6a0a0183269ce87af873767539

	public Admin(String userName, String password, String firstName, String lastName, String telephoneNumber,
			String profilePic, TreeMap<String,hikingHistory> hikingHistory, userStatus status) {
		super(userName, password, firstName, lastName, telephoneNumber, profilePic, hikingHistory, status);
		// TODO Auto-generated constructor stub
	}
	
	//public void editUser(User user, String name, String password, String firstName, 
	//String lastName, String telephoneNumber)
	
	public void setAdminStatus(User user, userStatus status) {
		if(!checkAdminStatus(user)) {
			user.setStatus(status);
		}
		
		
	}
	
	public userStatus statusConverter(String arg) {
		
		if(arg.toLowerCase().compareTo(userStatus.Admin.toString().toLowerCase())!=0) {
			return userStatus.Admin;
		}
		else
			return userStatus.nonAdmin;
//		switch(arg.toLowerCase()) {
//		case ("admin"): return userStatus.Admin;
//		case ("nonadmin"): return userStatus.nonAdmin;
//		default: return userStatus.nonAdmin;
//		}
		//return status;
	}
	
	

}
