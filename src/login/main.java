package login;

<<<<<<< HEAD
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
=======
import java.util.HashMap;
>>>>>>> b23556ec6f7f2a6a0a0183269ce87af873767539
import java.util.TreeMap;

import hikers.Admin;
import hikers.Difficulty;
import hikers.Trails;
<<<<<<< HEAD
import hikers.User;
import hikers.hikingHistory;
import hikers.hikingIO;
import hikers.singleton;
import hikers.trailType;
import hikers.userStatus;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
=======
import hikers.trailType;
import hikers.userStatus;
import hikingHistory.hikingHistory;
import javafx.application.Application;
>>>>>>> b23556ec6f7f2a6a0a0183269ce87af873767539
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class main extends Application{
	
	public static User userMan;
	
	public static TreeMap<String, User> users;
	public static HashMap<String, Trails> trails;
	public static TreeMap<String,hikingHistory> history;
<<<<<<< HEAD
	public static LinkedList<String> pictures;
=======
	
>>>>>>> b23556ec6f7f2a6a0a0183269ce87af873767539

	

	public static void main(String[] args) {
<<<<<<< HEAD
		//below are the methods that initialize the lists
		
//		trails=new HashMap<String, Trails>(500000);
//		
//		LinkedList<String> tryhard=new LinkedList<String>();
//		pictures=new LinkedList<String>();
//		pictures.add("/images/trailPic.jpg");
//		pictures.add("/images/trailPic2.jpg");
//		pictures.add("/images/trailPic3.jpg");
//		pictures.add("/images/trailPic4.jpg");
//		pictures.add("/images/trailPic5.jpg");
//		
//		history=new TreeMap<String,hikingHistory>();
//		
//		hikingHistory hist1=new hikingHistory("cream","02-01-21 6:00pm","02-01-21 9:00pm","23 mi","2",tryhard,"40mph");
//		hikingHistory hist2=new hikingHistory("cram","02-04-21 6:00pm","02-04-21 9:00pm","40 mi","3",tryhard,"40mph");
//		hikingHistory hist3=new hikingHistory("sam","02-09-21 6:00pm","02-09-21 9:00pm","50 mi","4",tryhard,"40mph");
//		
//		history.put(hist1.getDateAndStartTime(), hist1);
//		history.put(hist2.getDateAndStartTime(), hist2);
//		history.put(hist3.getDateAndStartTime(), hist3);
//		
//		TreeMap<String,hikingHistory> history2=new TreeMap<String,hikingHistory>();
//		
//		hikingHistory hist11=new hikingHistory("creep","02-01-21 6:00pm","02-01-21 9:00pm","23 mi","2",pictures,"30mph");
//		hikingHistory hist22=new hikingHistory("cryon","02-04-21 6:00pm","02-04-21 9:00pm","40 mi","3",tryhard,"50mph");
//		hikingHistory hist33=new hikingHistory("samuel","02-09-21 6:00pm","02-09-21 9:00pm","50 mi","4",tryhard,"60mph");
//		
//		history2.put(hist11.getDateAndStartTime(), hist11);
//		history2.put(hist22.getDateAndStartTime(), hist22);
//		history2.put(hist33.getDateAndStartTime(), hist33);
//		
//		//users start here
//		users=new TreeMap<String, User>();
//		User marco= new User("mike","pass","Marco","Polo","1234567890","/images/thomasMutherEngine.jpg",history,userStatus.nonAdmin);
//		Admin chad= new Admin("max","pain","Chad","drip","1234567890","/images/ronaldo.jpg",history2, userStatus.Admin);
//		//test user
//		userMan=chad;
//		
//		users.put(marco.getUserName(),marco);
//		users.put(chad.getUserName(),chad);
		
		
		
	try {
		 trails=hikingIO.getTrailData();
		  users=hikingIO.getUserData();
		 System.out.println(users);
	} catch (ClassNotFoundException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
		Application.launch(args);
=======
		// TODO Auto-generated method stub
		
		
		//below are the methods that initialize the lists
		
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
		
		TreeMap<String,hikingHistory> history2=new TreeMap<String,hikingHistory>();
		
		hikingHistory hist11=new hikingHistory("creep","02-01-21 6:00pm","02-01-21 9:00pm","23 mi","2","","30mph");
		hikingHistory hist22=new hikingHistory("cryon","02-04-21 6:00pm","02-04-21 9:00pm","40 mi","3","","50mph");
		hikingHistory hist33=new hikingHistory("samuel","02-09-21 6:00pm","02-09-21 9:00pm","50 mi","4","","60mph");
		
		history2.put(hist11.getTrailName(), hist11);
		history2.put(hist22.getTrailName(), hist22);
		history2.put(hist33.getTrailName(), hist33);
		
		//users start here
		users=new TreeMap<String, User>();
		User marco= new User("mike","pass","Marco","Polo","1234567890","insert photo",history,userStatus.nonAdmin);
		Admin chad= new Admin("max","pain","Chad","drip","1234567890","insert photo",history2, userStatus.Admin);
		//test user
		userMan=chad;
		
		users.put(marco.getUserName(), marco);
		users.put(chad.getUserName(), chad);
		
		Application.launch(args);

>>>>>>> b23556ec6f7f2a6a0a0183269ce87af873767539
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		Pane mainPane=(Pane) FXMLLoader.load(main.class.getResource("/view/sBLogin.fxml"));
=======
		Pane mainPane=(Pane) FXMLLoader.load(main.class.getResource("sBLogin.fxml"));
>>>>>>> b23556ec6f7f2a6a0a0183269ce87af873767539
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

	public static void setUserMan(User user) {
		userMan=user;
	}

	public static void setUsers(TreeMap<String, User> users) {
		main.users = users;
	}

	public static void setTrails(HashMap<String, Trails> trails) {
		main.trails = trails;
	}

	public static void setHistory(TreeMap<String, hikingHistory> history) {
		// TODO Auto-generated method stub
		main.history = history;
	}


<<<<<<< HEAD
=======


>>>>>>> b23556ec6f7f2a6a0a0183269ce87af873767539
}
