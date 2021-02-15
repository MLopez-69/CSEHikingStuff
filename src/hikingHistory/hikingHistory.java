package hikingHistory;

public class hikingHistory {
	private String trailName;
	private String dateAndStartTime;
	private String dateAndEndTime;
	private String distanceHiked;//in miles
	private String duration;
	//below the string is a place holder for an eventual picture bag?
	private String picturesTaken;
	private String averagePace;
	public hikingHistory() {
		super();
	}
	public hikingHistory(String trailName, String dateAndStartTime, String dateAndEndTime, String distanceHiked,
			String duration, String picturesTaken, String averagePace) {
		super();
		this.trailName = trailName;
		this.dateAndStartTime = dateAndStartTime;
		this.dateAndEndTime = dateAndEndTime;
		this.distanceHiked = distanceHiked;
		this.duration = duration;
		this.picturesTaken = picturesTaken;
		this.averagePace = averagePace;
	}
	public String getTrailName() {
		return trailName;
	}
	public void setTrailName(String trailName) {
		this.trailName = trailName;
	}
	public String getDateAndStartTime() {
		return dateAndStartTime;
	}
	public void setDateAndStartTime(String dateAndStartTime) {
		this.dateAndStartTime = dateAndStartTime;
	}
	public String getDateAndEndTime() {
		return dateAndEndTime;
	}
	public void setDateAndEndTime(String dateAndEndTime) {
		this.dateAndEndTime = dateAndEndTime;
	}
	public String getDistanceHiked() {
		return distanceHiked;
	}
	public void setDistanceHiked(String distanceHiked) {
		this.distanceHiked = distanceHiked;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getPicturesTaken() {
		return picturesTaken;
	}
	public void setPicturesTaken(String picturesTaken) {
		this.picturesTaken = picturesTaken;
	}
	public String getAveragePace() {
		return averagePace;
	}
	public void setAveragePace(String averagePace) {
		this.averagePace = averagePace;
	}
	@Override
	public String toString() {
		return "hikingHistory [trailName=" + trailName + ", dateAndStartTime=" + dateAndStartTime + ", dateAndEndTime="
				+ dateAndEndTime + ", distanceHiked=" + distanceHiked + ", duration=" + duration + ", picturesTaken="
				+ picturesTaken + ", averagePace=" + averagePace + "]";
	}
	
	
}
