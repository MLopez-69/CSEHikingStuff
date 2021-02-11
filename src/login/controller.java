package login;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeMap;

import hikers.Trails;
import hikingHistory.hikingHistory;

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
		 System.exit(0);
	    }

	    @FXML
	    void loginButtonPressed(ActionEvent event) throws IOException {
	    	
	    	//users.c
	    	
	    	if(users.containsKey(usernameField.getText())){
	    		user=users.get(usernameField.getText());
	    		if( user.checkPassword(passwordField.getText())) {
	    			main.setUserMan(user);
	    		
	    		FXMLLoader loader =new FXMLLoader();
		    	loader.setLocation(getClass().getResource("/login/home.fxml"));
		    	Parent root= loader.load();
		    	
		    	homeController controller= loader.getController();
		    	controller.setUser(user);
		        //controller.initTable(user,history, trails);
	    		//Parent root = FXMLLoader.load(getClass().getResource("/login/homeController.fxml"));
		        Scene scene = new Scene(root);
		        
		        
		        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        window.setScene(scene);
		        window.show();
	    	}
	    		
	    	}
	    	
	    	

	    }

	    @FXML
	    void signUpButtonPressed(ActionEvent event) throws IOException {
	    	
	    	Parent root = FXMLLoader.load(getClass().getResource("/login/signUp.fxml"));
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
			
			System.out.println(users);
			System.out.println(trails);
			System.out.println(history);
			System.out.println(userMan);
		}
		
		public User getUser() {
			return user;
		}
	    
	    
	 
	
	
}
