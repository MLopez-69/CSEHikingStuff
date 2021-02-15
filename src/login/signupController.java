package login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

<<<<<<< HEAD
import hikers.User;
import hikers.hikingHistory;
import hikers.userStatus;
=======
import hikers.userStatus;
import hikingHistory.hikingHistory;
>>>>>>> b23556ec6f7f2a6a0a0183269ce87af873767539
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class signupController implements Initializable {
	private TreeMap<String, User> users;
	
	private User userMan;
	
	private static String userName;
	
	@FXML private Label firstNameLabel;
	@FXML private Label lastNameLabel;
	@FXML private Label telephoneLabel;
	
	
	//this method accepts a person to initialize
	public void initData(User user) {
		userMan=user;
		firstNameLabel.setText(userMan.getFirstName());
		firstNameLabel.setText(userMan.getLastName());
		firstNameLabel.setText(userMan.getTelephoneNumber());
	}
	
	 @FXML
	    private HBox mainBox;

	    @FXML
	    private VBox fieldBox;

	    @FXML
	    private TextField usernameField;

	    @FXML
	    private TextField passwordField;

	    @FXML
	    private TextField fNameField;

	    @FXML
	    private TextField lNameField;

	    @FXML
	    private TextField phoneField;
	    
	    @FXML
	    private Button registerButton;



	    @FXML
	    void backTrack(ActionEvent event) throws IOException {
<<<<<<< HEAD
	    	Parent root = FXMLLoader.load(getClass().getResource("/view/sBLogin.fxml"));
=======
	    	Parent root = FXMLLoader.load(getClass().getResource("/login/sBLogin.fxml"));
>>>>>>> b23556ec6f7f2a6a0a0183269ce87af873767539
	        Scene scene = new Scene(root);
	        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        window.setScene(scene);
	        window.show();
	    }

	    @FXML
	    void registerNow(ActionEvent event) throws IOException {
	    	String userName=usernameField.getText();
	    	
	    	String password=passwordField.getText();
	    	
	    	String firstName=fNameField.getText();
	    	
	    	String lastName=lNameField.getText();
	    	
	    	String phoneNUmber=phoneField.getText();
	    	String picture="";
	    	//hikingHistory trail="";
	    	TreeMap<String, hikingHistory> history=new TreeMap<>();
	    	//
	    	User newUser=new User(userName,password,firstName,lastName,phoneNUmber,picture,
	    			 history, userStatus.nonAdmin);
	    	
	    	users.put(newUser.getUserName(), newUser);
	    	this.userName=userName;
	    	
	    	backTrack(event);
	    	
	    }
	    
	    @FXML
	    void exitProgram(ActionEvent event) {
		 System.exit(0);
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			users=main.getUsers();
			userMan=main.getUserMan();
		}
		

//		public static Object getUsername() {
//			// TODO Auto-generated method stub
//			return userName;
//		}
		
		
	    
	    

}
