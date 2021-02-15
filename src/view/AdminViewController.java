package view;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.function.Predicate;

import hikers.Admin;
import hikers.Difficulty;
import hikers.Trails;
import hikers.trailType;
import hikers.userStatus;
import hikingHistory.hikingHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import login.User;
import login.main;

public class AdminViewController implements Initializable{
	
	private HashMap<String,Trails> trails;
	private TreeMap<String, User> users;
	private TreeMap<String, hikingHistory> history;
	
	private User selectedPerson;
	private Trails selectedTrail;
	private hikingHistory selectedHistory;
	private Admin admin;
	private final ObservableList<Trails> trailList = FXCollections.observableArrayList();
	private final ObservableList<hikingHistory> hiking = FXCollections.observableArrayList();
	private final ObservableList<User> userList= FXCollections.observableArrayList();
	
	//user relkated functions
    @FXML
    private TableView<User> userTable;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> passwordColumn;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private TableColumn<User, String> phoneColumn;
    @FXML
    private TableColumn<User, String> statusColumn;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField userNameField;
    
    //history related functions

    @FXML
    private TableView<hikingHistory> historyTable;
    @FXML
    private TableColumn<hikingHistory, String> hikingNameColumn;
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
    private TextField searchField;
    @FXML
    private TextField dateStartField;
    @FXML
    private TextField dateEndField;
    @FXML
    private TextField distanceField;
    @FXML
    private TextField trailField;
    @FXML
    private TextField durationField;
    @FXML
    private TextField averagePaceField;
    
    //trail related stuff
    @FXML
    private TableView<Trails> trailTable;
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
    private TextField addressField;
    @FXML
    private TextField elevationField;
    @FXML
    private TextField lengthField;
    @FXML
    private TextField trailNameField;
    @FXML
    private ComboBox<trailType> trailTypeBox;

    @FXML
    private ComboBox<Difficulty> difficultyBox;
    
    @FXML
    void backTrack(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/login/home.fxml"));
        Scene scene = new Scene(root);
        
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void deleteHistory(ActionEvent event) {
    	history.remove(historyTable.getSelectionModel().getSelectedItem().getTrailName(), 
    			historyTable.getSelectionModel().getSelectedItem());
    	
    	hiking.remove(historyTable.getSelectionModel().getSelectedItem());
    	System.out.println(hiking);

    }

    @FXML
    void deleteTrail(ActionEvent event) {
    	trails.remove(trailTable.getSelectionModel().getSelectedItem());
    	trailList.remove(trailTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    void editHistory(ActionEvent event) {
    	hikingHistory updatedHist= new hikingHistory(trailField.getText(),dateStartField.getText(),
    			dateEndField.getText(),distanceField.getText(),durationField.getText(),"",
    			averagePaceField.getText());
    	
    	history.remove(selectedHistory.getTrailName(), selectedHistory);
    	hiking.remove(selectedHistory);
    	hiking.add(updatedHist);
    	history.put(updatedHist.getTrailName(),updatedHist);
    	selectedPerson.setHikingHistory(history);
    }

    @FXML
    void editTrail(ActionEvent event) {
    	Trails updatedTrail= new Trails(trailNameField.getText(),addressField.getText(),
    			lengthField.getText(),elevationField.getText(),difficultyBox.getValue(),trailTypeBox.getValue());
    	
    	trails.remove(selectedTrail.getTrailName(), selectedTrail);
    	trailList.remove(selectedTrail);
    	trailList.add(updatedTrail);
    	trails.put(updatedTrail.getTrailName(),updatedTrail);
    }
    
    @FXML
    void addTrail(ActionEvent event) {
    	Trails trail=new Trails(trailNameField.getText(),addressField.getText(),lengthField.getText()
    			,elevationField.getText(),Difficulty.EASY,trailType.LOOP);
    	
    	//get items from table
    	this.trails.put(trailNameField.getText(), trail);
    	
    	trailTable.getItems().add(trail);
    }


    @FXML
    void editUserProfile(ActionEvent event) {
    	//System.out.println(statusComboBox.getValue()); 
    	User updatedUser= new User(userNameField.getText(),passwordField.getText(),firstNameField.getText(),
    			lastNameField.getText(),phoneNumber.getText(),"",history,
    			admin.statusConverter(statusComboBox.getValue()));
    	
    	users.remove(selectedPerson.getUserName(), selectedPerson);
    	userList.remove(selectedPerson);
    	userList.add(updatedUser);
    	users.put(updatedUser.getUserName(),updatedUser);
    }

    @FXML
    void removeUser(ActionEvent event) {
    	users.remove(userTable.getSelectionModel().getSelectedItem().getUserName(),
    			userTable.getSelectionModel().getSelectedItem());
    	userList.remove(userTable.getSelectionModel().getSelectedItem());
    	//userTable.getItems().removeAll(userTable.getSelectionModel().getSelectedItem());
    }
    
    //MOUSE events--------------------------------------------------------------------------------
    @FXML
    void editUserClick(MouseEvent event) {
    	if(event.getClickCount()==2) {
    		selectedPerson=userTable.getSelectionModel().getSelectedItem();
    		userNameField.setText(selectedPerson.getUserName());
    		firstNameField.setText(selectedPerson.getFirstName());
    		lastNameField.setText(selectedPerson.getLastName());
    		passwordField.setText(selectedPerson.getPassword());
    		phoneNumber.setText(selectedPerson.getTelephoneNumber());
    		//REMEBER TO CHANGE THIS PART
    		statusComboBox.setValue(selectedPerson.getStatus().toString());
    		history=selectedPerson.getHikingHistory();
    		hiking.clear();
    		iterateHistoryMap();
    		
    	}
    }
    
    @FXML
    void editHistoryClick(MouseEvent event) {
    	if(event.getClickCount()==2) {
    		selectedHistory=historyTable.getSelectionModel().getSelectedItem();
    		trailField.setText(selectedHistory.getTrailName());
    		distanceField.setText(selectedHistory.getDistanceHiked());
    		dateStartField.setText(selectedHistory.getDateAndStartTime());
    		dateEndField.setText(selectedHistory.getDateAndEndTime());
    		durationField.setText(selectedHistory.getDuration());
    		averagePaceField.setText(selectedHistory.getAveragePace());
    		
    	}
    }
    
    @FXML
    void editTrailClick(MouseEvent event) {
    	if(event.getClickCount()==2) {
    		selectedTrail=trailTable.getSelectionModel().getSelectedItem();
    		trailNameField.setText(selectedTrail.getTrailName());
    		addressField.setText(selectedTrail.getAddress());
    		elevationField.setText(selectedTrail.getElevationInFeet());
    		lengthField.setText(selectedTrail.getMiles());
    		difficultyBox.setValue(selectedTrail.getDifficultyLevel());
    		trailTypeBox.setValue(selectedTrail.getType());
    		
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		users=main.getUsers();
		trails=main.getTrails();
		//history=main.getHistory();
		admin=(Admin) main.getUserMan();
		System.out.println(selectedPerson);
		
		statusComboBox.getItems().addAll(userStatus.Admin.toString(),userStatus.nonAdmin.toString());
		difficultyBox.getItems().addAll(Difficulty.EASY,Difficulty.MODERATE,Difficulty.HARD);
		trailTypeBox.getItems().addAll(trailType.LOOP,trailType.OUTANDBACK,trailType.POINTTOPOINT);
		
		initData();
		
		//wraps observableList

		FilteredList<Trails> filteredData=new FilteredList<>(trailList);
		//setting filter predicate
		searchField.textProperty().addListener((observable, oldValue, newValue)->{
			filteredData.setPredicate(createPredicate(newValue));
		});
		
		//Sorted list
		SortedList<Trails> sortedData=new SortedList<>(filteredData);
		//stuffer
		sortedData.comparatorProperty().bind(trailTable.comparatorProperty());
		trailTable.setItems(sortedData);
		
	}
	
	//below is for the filtered search
	private boolean searchFindsOrder(Trails trail, String searchText) {
		return (trail.getTrailName().toLowerCase().contains(searchText.toLowerCase()));
	}
	
	private Predicate<Trails> createPredicate(String searchText){
		return trail->{
			if(searchText==null||searchText.isEmpty()) return true;
			return searchFindsOrder(trail,searchText);
		};
	}
	
	//below are iterators-----------------------------------------------------------------------
		public void iterateUserMap() {
			Iterator<Map.Entry<String,User>> userIterator=users.entrySet().iterator();
			while(userIterator.hasNext()) {
				Map.Entry<String, User> entry=userIterator.next();
				userList.add(entry.getValue());
			}
		}
		
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
				hiking.add(entry.getValue());
			}
			}
			
		}
		// the following sets the data i the table view-----------------------------------
		
		public void initData() {
			iterateUserMap();
			
			usernameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("userName"));
			passwordColumn.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
			firstNameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("firstName"));
			lastNameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("lastName"));
			phoneColumn.setCellValueFactory(new PropertyValueFactory<User,String>("telephoneNumber"));
			statusColumn.setCellValueFactory(new PropertyValueFactory<User,String>("lastName"));
			
			userTable.setItems(userList);
			
			iterateHistoryMap();
		
			
			hikingNameColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("trailName"));
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
			trailTable.setItems(trailList);
		}
	
	
	
	
	
}
