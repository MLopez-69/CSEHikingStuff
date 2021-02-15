package hikers;

import java.io.Serializable;

public class Trails implements Serializable{
	private String trailName;
	private String address;
	private String miles;
	private String elevationInFeet;
	private Difficulty difficultyLevel;	//difficultylevel (easy, moderate, hard)
	private trailType type;//type (loop, out and back, and point-to-point)
	public Trails() {
		super();
	}
	public Trails(String trailName, String address, String miles, String elevationInFeet, Difficulty difficultyLevel,
			trailType type) {
		super();
		this.trailName = trailName;
		this.address = address;
		this.miles = miles;
		this.elevationInFeet = elevationInFeet;
		this.difficultyLevel = difficultyLevel;
		this.type = type;
	}
	public String getTrailName() {
		return trailName;
	}
	public void setTrailNam(String trailName) {
		this.trailName = trailName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMiles() {
		return miles;
	}
	public void setMiles(String miles) {
		this.miles = miles;
	}
	public String getElevationInFeet() {
		return elevationInFeet;
	}
	public void setElevationInFeet(String elevationInFeet) {
		this.elevationInFeet = elevationInFeet;
	}
	public Difficulty getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(Difficulty difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	public trailType getType() {
		return type;
	}
	public void setType(trailType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Trails [trailNam=" + trailName + ", address=" + address + ", miles=" + miles + ", elevationInFeet="
				+ elevationInFeet + ", difficultyLevel=" + difficultyLevel + ", type=" + type + "]";
	}
	
	

}
