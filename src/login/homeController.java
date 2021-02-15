package login;


import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.function.Predicate;

import javax.imageio.ImageIO;

import hikers.Difficulty;
import hikers.Trails;
import hikers.User;
import hikers.hikingHistory;
import hikers.hikingIO;
import hikers.trailType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.hikingHistoryController;

public class homeController implements Initializable{
	private HashMap<String,Trails> trails;
	private TreeMap<String, User> users;
	private TreeMap<String, hikingHistory> history;
	
	private String userName;
	private User selectedPerson;
	public hikingHistory hiked;
	private final ObservableList<Trails> trailList = FXCollections.observableArrayList();
	private final ObservableList<hikingHistory> hiking = FXCollections.observableArrayList();
	private final ObservableList<User> userList= FXCollections.observableArrayList();
	private FilteredList<Trails> filteredData;
	private boolean isTrailEmpty;
	SortedList<Trails> sortedData;
	
	
	//all things below here are for the user, such as user info
	@FXML
    private Label firstNameLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private ImageView userImage;
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField userNameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField pictureField;
    //all things below here are for the trails tab
    
    @FXML
    private TableView<Trails> trailTableView;
    @FXML
    private TableColumn<Trails, String> trailNameColumn;
    @FXML
    private TableColumn<Trails, String> addressColumn;
    @FXML
    private TableColumn<Trails, String> lengthColumn;
    @FXML
    private TableColumn<Trails, String> elevationColumn;
    @FXML
    private TableColumn<Trails, String> difficultyColumn;
    @FXML
    private TableColumn<Trails, String> typeColumn;
    
    @FXML
    private ComboBox<Difficulty> difficultyComboBox;
     @FXML
    private ComboBox <trailType>comboBox;
    @FXML
    private ComboBox<String> lengthBox;
    @FXML
    private ComboBox<String> elevationBox;
    private TextField searchField;
    
    
    //below is for hiking history
    
    @FXML
    private TableView<hikingHistory> historyTable;
    @FXML
    private TableColumn<hikingHistory, String> historyName;
    @FXML
    private TableColumn<hikingHistory, String> dateStartColumn;
    @FXML
    private TableColumn<hikingHistory, String> dateEndColumn;
    @FXML
    private TableColumn<hikingHistory, String> distanceHikedColumn;
    @FXML
    private TableColumn<hikingHistory, String> durationColumn;
    @FXML
    private TableColumn<hikingHistory, String> paceColumn;
    
    @FXML
    private Button adminBtn;
    @FXML
    private File filePath;

    
    //start of methods
    public void setUser(User user) {
    	selectedPerson=user;
    }
    
    @FXML
    void goToAdminPage(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/view/adminFunctionView.fxml"));
        Scene scene = new Scene(root);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    //the method below allows for users to search for the trail through a button
    
    @FXML
    void searchTable(ActionEvent event) {
    	//wraps observableList
    	setTrailsVisible(true);
    }
    
    @FXML
    void filterTable(ActionEvent event) {
    	difficultyComboBox.valueProperty().addListener((observable, oldValue, newValue)->{
		filteredData.setPredicate(createDifficultyPredicate(newValue));
	});
    }
    
    @FXML
    void choosePic(ActionEvent event) throws IOException {
    	Stage stage= (Stage)((Node)event.getSource()).getScene().getWindow();
    	FileChooser fc= new FileChooser();
    	fc.setTitle("open File Dialog");
    	String userDirectoryString =System.getProperty("user.home");
    	File userDirectory=new File(userDirectoryString);
    	
    	if(!userDirectory.canRead()) {
    		userDirectory=new File("c:/");
    	}
    	fc.setInitialDirectory(userDirectory);
		this.filePath=fc.showOpenDialog(stage);
    	
    	//try to update image
    	try {
    		BufferedImage buffedImage=ImageIO.read(filePath);
    		Image crime=SwingFXUtils.toFXImage(buffedImage, null);
    		this.userImage.setImage(crime);
    		selectedPerson.setProfilePic(filePath.getAbsolutePath());
    	}catch(IOException e) {
    		System.err.println(e.getMessage());
    	}
    	
    }
    
    @FXML
    void changeToEditHistory(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/hikingHistoryView.fxml"));
        Scene scene = new Scene(root);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    void changeToHistoryView(MouseEvent event) throws IOException {
    	if (event.getClickCount()==2) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/historyPopup.fxml"));
			Parent root = loader.load();
			
			//Parent root = FXMLLoader.load(getClass().getResource("/login/homeController.fxml"));
			Scene scene = new Scene(root);
			historyPopController controller = loader.getController();
			
			try {
				controller.setHiked(historyTable.getSelectionModel().getSelectedItem(),selectedPerson);
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		}
    	
    }
    
    @FXML
    void signOut(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/view/sBLogin.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    void signOutQuit(ActionEvent event) throws IOException {
    	try {
			hikingIO.saveTrailData(trails);
			hikingIO.saveUserData(users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}	
			
    	System.exit(0);
    }
    
    @FXML
    void startEdit(ActionEvent event) {
    	firstNameField.setDisable(false);
    	lastNameField.setDisable(false);
    	userNameField.setDisable(false);
    	passwordField.setDisable(false);
    	phoneField.setDisable(false);
    	pictureField.setDisable(false);
    	
    }
    @FXML
    void updateUser(ActionEvent event) {
    	String picture="";
    	if(pictureField.getText()==null||!pictureField.getText().isEmpty()) {
    		picture=selectedPerson.getProfilePic();
    	}
    	else {
    		picture=pictureField.getText();
    	}
    	User updatedUser= new User(userNameField.getText(),passwordField.getText(),firstNameField.getText(),
    			lastNameField.getText(),phoneField.getText(),picture,history,
    			selectedPerson.getStatus());
    	
    	users.remove(selectedPerson.getUserName(), selectedPerson);
    	userList.remove(selectedPerson);
    	userList.add(updatedUser);
    	users.put(updatedUser.getUserName(),updatedUser);
    	selectedPerson=updatedUser;
    	disableUserField();
    }
    
    public void comboBoxWasUpdated() {
    	comboBox.valueProperty().addListener((observable, oldValue, newValue)->{
    		filteredData.setPredicate(createTypePredicate(newValue));
    	}); 
    	
    }
    
    @FXML
    void elevationBoxUpdated(ActionEvent event) {
    	elevationBox.valueProperty().addListener((observable, oldValue, newValue)->{
    		filteredData.setPredicate(createElevationPredicate(newValue));
    }); 	
    	
    }
    
    @FXML
    void lengthBoxUpdated(ActionEvent event) {
    	lengthBox.valueProperty().addListener((observable, oldValue, newValue)->{
    		filteredData.setPredicate(createLengthPredicate(newValue));
    }); 
    	
    	
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		users=main.getUsers();
		trails=main.getTrails();

		selectedPerson=main.getUserMan();
		history=selectedPerson.getHikingHistory();
		
		Image userIm=new Image(getClass().getResource
				(selectedPerson.getProfilePic()).toExternalForm());
		
		userImage.setImage(userIm);
		
		if(!selectedPerson.checkAdminStatus()){
			adminBtn.setVisible(false);
		}
		else
			adminBtn.setVisible(true);
		//sets data and stuff
		initBoxes();
		initData();
		

		if(!selectedPerson.checkAdminStatus())
		{
			adminBtn.setDisable(true);
		}
		else
			adminBtn.setDisable(false);
		
		initBoxes();
		initData();
	
	//wraps observableList
	filteredData=new FilteredList<>(trailList);

	//setting filter predicate
	searchField.textProperty().addListener((observable, oldValue, newValue)->{
		filteredData.setPredicate(createPredicate(newValue));
	});
	
	//Sorted list
	SortedList<Trails> sortedData=new SortedList<>(filteredData);
	//stuffer
	sortedData.comparatorProperty().bind(trailTableView.comparatorProperty());
	
	setTrailsVisible(false);
	trailTableView.setItems(sortedData);
	
		System.out.println(users);
		System.out.println(trails);
		System.out.println(history+"\n");
		
	}
	
	//below is used for search
	private boolean searchFindsOrder(Trails trail, String searchText) {
		return (trail.getTrailName().toLowerCase().contains(searchText.toLowerCase()));
	}
	
	private boolean searchFindsLength(Trails trail, String searchText) {
		int mile=Integer.parseInt(trail.getMiles());
		return ((searchText.contentEquals("small lengths")&&mile<100)||
				(searchText.contentEquals("medium length")&&(mile>100 &&mile<250 ))||
				(searchText.contentEquals("long length")&&(mile>250)));
	}
	
	private boolean searchFindsElevation(Trails trail, String searchText) {
		int mile=Integer.parseInt(trail.getElevationInFeet());
		return ((searchText.contentEquals("small rise")&&mile<50)||
				(searchText.contentEquals("medium rise")&&(mile>50 &&mile>100))||
				(searchText.contentEquals("high rise")&&(mile>100)));
	}
	
	private boolean searchFindsOrder(Trails trail, Difficulty searchIndex) {
		if(trail.getDifficultyLevel().compareTo(searchIndex)==0) {
			return true;
		}
		else
			return false;
	}
	
	private boolean searchFindsOrder(Trails trail, trailType searchIndex) {
		if(trail.getType().compareTo(searchIndex)==0) {
			return true;
		}
		else
			return false;
	}
	private Predicate<Trails> createPredicate(String searchText){
		return trail->{
			if(searchText==null||searchText.isEmpty()) {
				setTrailsVisible(false);
				return true;
			}
			setTrailsVisible(true);
			return searchFindsOrder(trail,searchText);
		};
	}
	private Predicate<Trails> createLengthPredicate(String searchText){
		return trail->{
			if(searchText==null||searchText.isEmpty()) {
				setTrailsVisible(false);
				return true;
			}
			setTrailsVisible(true);
			return searchFindsLength(trail,searchText);
		};
	}
	private Predicate<Trails> createElevationPredicate(String searchText){
		return trail->{
			if(searchText==null||searchText.isEmpty()) {
				setTrailsVisible(false);
				return true;
			}
			setTrailsVisible(true);
			return searchFindsElevation(trail,searchText);
		};
	}
	private Predicate<Trails> createDifficultyPredicate(Difficulty searchIndex){
		return trail->{
			if(searchIndex==null) {
				setTrailsVisible(false);
				return true;
			}
			setTrailsVisible(true);
			return searchFindsOrder(trail,searchIndex);
		};
	}
	
	private Predicate<Trails> createTypePredicate(trailType searchIndex){
		return trail->{
			if(searchIndex==null) {
				setTrailsVisible(false);
				return true;
			}
			setTrailsVisible(true);
			return searchFindsOrder(trail,searchIndex);
		};
	}
	
	
	//below are iterators
	
	public void iterateTrailMap() {
		Iterator<Map.Entry<String,Trails>> trailIterate=trails.entrySet().iterator();
		while(trailIterate.hasNext()) {
			Map.Entry<String, Trails> entry=trailIterate.next();
			trailList.add(entry.getValue());
		}
	}
	
	public void iterateHistoryMap() {
		if(history!=null) {
		Iterator<Map.Entry<String,hikingHistory>> historyIterator=history.entrySet().iterator();
		while(historyIterator.hasNext()) {
			Map.Entry<String, hikingHistory> entry=historyIterator.next();
			hiking.add(entry.getValue());} 
		}
	}
	
	public void disableUserField() {
		firstNameField.setDisable(true);;
		lastNameField.setDisable(true);
		userNameField.setDisable(true);
		passwordField.setDisable(true);
		phoneField.setDisable(true);
		pictureField.setDisable(true);
		}
		
	}
	
	public void initBoxes() {
		firstNameLabel.setText(selectedPerson.getFirstName());
		comboBox.getItems().addAll(trailType.LOOP,
		trailType.OUTANDBACK,trailType.POINTTOPOINT);
		difficultyComboBox.getItems().addAll(Difficulty.EASY,
				Difficulty.MODERATE,Difficulty.HARD);
		lengthBox.getItems().addAll("small length","medium length","long length");
		elevationBox.getItems().addAll("small rise","medium rise","high rise");
	}
	// the following sets the data i the table view
	
	public void initData() {
		
		firstNameField.setText(selectedPerson.getFirstName());
		lastNameField.setText(selectedPerson.getLastName());
		userNameField.setText(selectedPerson.getUserName());
		passwordField.setText(selectedPerson.getPassword());
		phoneField.setText(selectedPerson.getTelephoneNumber());
		pictureField.setText(selectedPerson.getProfilePic());
		disableUserField();	
		
		iterateHistoryMap();
		
		historyName.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("trailName"));
		dateStartColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("dateAndStartTime"));
		dateEndColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("dateAndEndTime"));
		distanceHikedColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("distanceHiked"));
		durationColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("duration"));
		paceColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("averagePace"));
		historyTable.setItems(hiking);
		
		//hashmap implement trails table here
		
		iterateTrailMap();
	
		trailNameColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("trailName"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("address"));
		lengthColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("miles"));
		elevationColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("elevationInFeet"));
		difficultyColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("difficultyLevel"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("type"));
		//trailTableView.setItems(trailList);
		
	}
	
	public void setTrailsVisible(boolean setTrails) {
		trailNameColumn.setVisible(setTrails);
		addressColumn.setVisible(setTrails);
		lengthColumn.setVisible(setTrails);
		elevationColumn.setVisible(setTrails);
		difficultyColumn.setVisible(setTrails);
		typeColumn.setVisible(setTrails);
	}
	
	
	
}
