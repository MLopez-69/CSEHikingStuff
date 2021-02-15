package login;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeMap;

import hikers.Trails;
<<<<<<< HEAD
import hikers.User;
import hikers.hikingHistory;
import hikers.hikingIO;
=======
import hikingHistory.hikingHistory;
>>>>>>> b23556ec6f7f2a6a0a0183269ce87af873767539

//import org.w3c.dom.Node;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class controller implements Initializable{
	private HashMap<String,Trails> trails;
	private TreeMap<String, User> users;
	private TreeMap<String, hikingHistory> history;
	
	private User user;
	private User userMan;
	
	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;
	
	 @FXML
	    private MenuItem exit;
	 
	 @FXML
	    void exitProgram(ActionEvent event) {
<<<<<<< HEAD
			try {
			hikingIO.saveTrailData(trails);
			hikingIO.saveUserData(users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
=======
>>>>>>> b23556ec6f7f2a6a0a0183269ce87af873767539
		 System.exit(0);
	    }

	    @FXML
	    void loginButtonPressed(ActionEvent event) throws IOException {
	    	
<<<<<<< HEAD
=======
	    	//users.c
	    	
>>>>>>> b23556ec6f7f2a6a0a0183269ce87af873767539
	    	if(users.containsKey(usernameField.getText())){
	    		user=users.get(usernameField.getText());
	    		if( user.checkPassword(passwordField.getText())) {
	    			main.setUserMan(user);
	    		
	    		FXMLLoader loader =new FXMLLoader();
<<<<<<< HEAD
		    	loader.setLocation(getClass().getResource("/view/home.fxml"));
		    	Parent root= loader.load();
		    	
=======
		    	loader.setLocation(getClass().getResource("/login/home.fxml"));
		    	Parent root= loader.load();
		    	
		    	homeController controller= loader.getController();
		    	controller.setUser(user);
		        //controller.initTable(user,history, trails);
	    		//Parent root = FXMLLoader.load(getClass().getResource("/login/homeController.fxml"));
>>>>>>> b23556ec6f7f2a6a0a0183269ce87af873767539
		        Scene scene = new Scene(root);
		        
		        
		        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        window.setScene(scene);
		        window.show();
	    	}
	    		
	    	}
	    	
<<<<<<< HEAD
=======
	    	
>>>>>>> b23556ec6f7f2a6a0a0183269ce87af873767539

	    }

	    @FXML
	    void signUpButtonPressed(ActionEvent event) throws IOException {
	    	
<<<<<<< HEAD
	    	Parent root = FXMLLoader.load(getClass().getResource("/view/signUp.fxml"));
=======
	    	Parent root = FXMLLoader.load(getClass().getResource("/login/signUp.fxml"));
>>>>>>> b23556ec6f7f2a6a0a0183269ce87af873767539
	        Scene scene = new Scene(root);
	        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        window.setScene(scene);
	        window.show();
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			users=main.getUsers();
			trails=main.getTrails();
			history=main.getHistory();
			userMan=main.getUserMan();
			
<<<<<<< HEAD
//			System.out.println(users);
//			System.out.println(trails);
//			System.out.println(history);
//			System.out.println(userMan);
=======
			System.out.println(users);
			System.out.println(trails);
			System.out.println(history);
			System.out.println(userMan);
>>>>>>> b23556ec6f7f2a6a0a0183269ce87af873767539
		}
		
		public User getUser() {
			return user;
		}
	    
	    
	 
	
	
}
