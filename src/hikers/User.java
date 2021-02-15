package hikers;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.TreeMap;

public class User implements Serializable{
	
	private String userName;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String telephoneNumber;
	
	private userStatus status;
	//LinkedList<String>
	private String profilePic;
	private TreeMap<String,hikingHistory> hikingHistory;
	
	public User() {
		
	}
	
	public User(String userName, String password, String firstName, String lastName, String telephoneNumber,
			String profilePic, TreeMap<String,hikingHistory> hikingHistory, userStatus status) {
		super();
		this.userName = userName;
		this.password = password;
		
		this.firstName = firstName;
		this.lastName = lastName;
		
		this.telephoneNumber = telephoneNumber;
		this.profilePic = profilePic;
		
		this.hikingHistory = hikingHistory;
		
		this.status=status;
	}
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	
	public TreeMap<String,hikingHistory> getHikingHistory() {
		return hikingHistory;
	}
	public void setHikingHistory(TreeMap<String,hikingHistory> hikingHistory) {
		this.hikingHistory = hikingHistory;
	}
	
	public void setStatus(userStatus status) {
		this.status=status;
	}
	
	
	public userStatus getStatus() {
		return status;
	}
	
	
	public boolean checkAdminStatus(User user) {
		//userStatus state=userStatus.Admin;
		String userStats=user.getStatus().toString().toLowerCase();
		String checker= userStatus.Admin.toString().toLowerCase();
		if(userStats.compareTo(checker)!=0) {
			return true;
		}
		else
			return false;
	}
	public boolean checkAdminStatus() {
		//userStatus state=userStatus.Admin;
		String userStats=status.toString().toLowerCase();
		String checker= userStatus.Admin.toString().toLowerCase();
		//status.com
		if(userStats.compareTo(checker)!=0) {
			return false;
		}
		else
			return true;
	}
	

	//maybe change to switch later
	public boolean checkPassword(String password) {
		if(this.password.compareTo(password)!=0)
		{
			return false;
		}
			
		else
			return true;
		
	}
	
//	public userStatus statusConverter(String arg) {
//		switch(arg.toLowerCase()) {
//		case ("admin"): return userStatus.Admin;
//		case ("nonadmin"): return userStatus.nonAdmin;
//		default: return userStatus.nonAdmin;
//		}
//	}
	
	
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", telephoneNumber=" + telephoneNumber + ", profilePic=" + profilePic + ", hikingHistory="
				+ hikingHistory + "]";
	}
	
	
	
	
}
