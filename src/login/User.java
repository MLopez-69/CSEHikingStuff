package login;

import hikers.userStatus;

public class User {
	
	private String userName;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String telephoneNumber;
	
	private userStatus status;
	//these is a placeholder for now
	
	private String profilePic;
	private String hikingHistory;
	
	public User() {
		
	}
	
	public User(String userName, String password, String firstName, String lastName, String telephoneNumber,
			String profilePic, String hikingHistory, userStatus status) {
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
	
	public String getHikingHistory() {
		return hikingHistory;
	}
	public void setHikingHistory(String hikingHistory) {
		this.hikingHistory = hikingHistory;
	}
	
	private void setStatus(userStatus status) {
		this.status=status;
	}
	
	
	public userStatus getStatus() {
		return status;
	}
	
	
	public boolean checkAdminStatus(User user) {
		//userStatus state=userStatus.Admin;
		String userStats=user.getStatus().toString();
		String checker= userStatus.Admin.toString();
		if(userStats.compareTo(checker)!=0) {
			return true;
		}
		else
			return false;
	}
	
	public void setAdminStatus(User user, userStatus status) {
		if(checkAdminStatus(user)) {
			user.setStatus(status);
		}
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
	
	
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", telephoneNumber=" + telephoneNumber + ", profilePic=" + profilePic + ", hikingHistory="
				+ hikingHistory + "]";
	}
	
	
	
	
}
