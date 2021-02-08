package login;

import java.util.HashMap;
import java.util.TreeMap;

import hikers.Admin;
import hikers.Difficulty;
import hikers.Trails;
import hikers.trailType;
import hikers.userStatus;
import hikingHistory.hikingHistory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class main extends Application{
	
	public static User userMan;
	
	public static TreeMap<String, User> users;
	public static HashMap<String, Trails> trails;
	public static TreeMap<String,hikingHistory> history;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//below are the methods that initialize the lists
		
		users=new TreeMap<String, User>();
		User marco= new User("mike","pass","Marco","Polo","1234567890","insert photo","",userStatus.nonAdmin);
		Admin chad= new Admin("max","pain","Chad","drip","1234567890","insert photo","", userStatus.Admin);
		//test user
		userMan=chad;
		
		users.put(marco.getUserName(), marco);
		users.put(chad.getUserName(), chad);
		
		trails=new HashMap<String, Trails>(500000);
		
		Trails trail1=new Trails("cameo","address","230","140",Difficulty.EASY,trailType.LOOP);
		Trails trail2=new Trails("prime","118 buffalo","230","140",Difficulty.MODERATE,trailType.OUTANDBACK);
		Trails trail3=new Trails("hammmer","resting road","230","140",Difficulty.HARD,trailType.POINTTOPOINT);
		
		trails.put(trail1.getTrailName(), trail1);
		trails.put(trail2.getTrailName(), trail2);
		trails.put(trail3.getTrailName(), trail3);
		
		history=new TreeMap<String,hikingHistory>();
		
		hikingHistory hist1=new hikingHistory("cream","02-01-21 6:00pm","02-01-21 9:00pm","23 mi","2","","40mph");
		hikingHistory hist2=new hikingHistory("cram","02-04-21 6:00pm","02-04-21 9:00pm","40 mi","3","","40mph");
		hikingHistory hist3=new hikingHistory("sam","02-09-21 6:00pm","02-09-21 9:00pm","50 mi","4","","40mph");
		
		history.put(hist1.getTrailName(), hist1);
		history.put(hist2.getTrailName(), hist2);
		history.put(hist3.getTrailName(), hist3);
		
		
		
		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pane mainPane=(Pane) FXMLLoader.load(main.class.getResource("sBLogin.fxml"));
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.show();
		
	}
	
	public static TreeMap<String, User> getUsers() {
		return users;
	}
	
	public static HashMap<String,Trails> getTrails(){
		return trails;
		
	}
	
	public static TreeMap<String, hikingHistory> getHistory() {
		return history;
	}
	
	public static User getUserMan() {
	return userMan;
}



}
