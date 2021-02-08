package login;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.function.Predicate;

import hikers.Difficulty;
import hikers.Trails;
import hikers.trailType;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import view.hikingHistoryController;

public class homeController implements Initializable{
	private HashMap<String,Trails> trails;
	private TreeMap<String, User> users;
	private TreeMap<String, hikingHistory> history;
	
	private String userName;
	private User selectedPerson;
	private User userMan;
	private final ObservableList<Trails> trailList = FXCollections.observableArrayList();
	private final ObservableList<hikingHistory> hiking = FXCollections.observableArrayList();
	private final ObservableList<User> userList= FXCollections.observableArrayList();
	
	
	//all things below here are for the user, such as user info
	@FXML
    private Label firstNameLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private TableView<User> personTableView;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> telephoneColumn;
    
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
    private ComboBox<String> difficultyComboBox;
     @FXML
    private ComboBox <String>comboBox;
    @FXML
    private Label comboLabel;
    
    @FXML
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
    
    

    
    //start of methods
    public void setUser(User user) {
    	selectedPerson=user;
    }
    
    
    
    //the method below allows for users to search for the trail through a button
    //****UNCOMMENT BELOW***
    
    @FXML
    void searchTable(ActionEvent event) {
    	//wraps observableList
    	System.out.println(trailList);
    	FilteredList<Trails> filteredData=new FilteredList<>(trailList);
    	//setting filter predicate
    	searchField.textProperty().addListener((observable, oldValue, newValue)->{
    		filteredData.setPredicate(createPredicate(newValue));
    	});
    	
    	//Sorted list
    	SortedList<Trails> sortedData=new SortedList<>(filteredData);
    	//stuffer
    	sortedData.comparatorProperty().bind(trailTableView.comparatorProperty());
    	trailTableView.setItems(sortedData);
    }
    
    @FXML
    void changeToEditHistory(ActionEvent event) throws IOException {
    	FXMLLoader loader =new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/hikingHistoryView.fxml"));
    	Parent root= loader.load();
    	
    	hikingHistoryController controller= loader.getController();
		//Parent root = FXMLLoader.load(getClass().getResource("/login/homeController.fxml"));
        Scene scene = new Scene(root);
        
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    
    public void comboBoxWasUpdated() {
    	this.comboLabel.setText("user "+comboBox.getValue().toString());
    }
    
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		users=main.getUsers();
		trails=main.getTrails();
		history=main.getHistory();
		userMan=main.getUserMan();
		
		comboBox.getItems().addAll(trailType.LOOP.toString(),
		trailType.OUTANDBACK.toString(),trailType.POINTTOPOINT.toString(),"none");
		
		difficultyComboBox.getItems().addAll(Difficulty.EASY.toString(),
				Difficulty.MODERATE.toString(),Difficulty.HARD.toString(),"none");
		
		
		Iterator<Map.Entry<String,User>> userIterator=users.entrySet().iterator();
		while(userIterator.hasNext()) {
			Map.Entry<String, User> entry=userIterator.next();
			userList.add(entry.getValue());
		}
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("lastName"));
		usernameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("userName"));
		telephoneColumn.setCellValueFactory(new PropertyValueFactory<User,String>("telephoneNumber"));
		personTableView.setItems(userList);
		
		Iterator<Map.Entry<String,hikingHistory>> historyIterator=history.entrySet().iterator();
		while(historyIterator.hasNext()) {
			Map.Entry<String, hikingHistory> entry=historyIterator.next();
			hiking.add(entry.getValue());
		}
	
		
		historyName.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("trailName"));
		dateStartColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("dateAndStartTime"));
		dateEndColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("dateAndEndTime"));
		distanceHikedColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("distanceHiked"));
		durationColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("duration"));
		paceColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("averagePace"));
		historyTable.setItems(hiking);
		
		//hashmap implement trails table here
		
		
		Iterator<Map.Entry<String,Trails>> trailIterate=trails.entrySet().iterator();
		while(trailIterate.hasNext()) {
			Map.Entry<String, Trails> entry=trailIterate.next();
			trailList.add(entry.getValue());
		}
	
	
	
	trailNameColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("trailName"));
	addressColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("address"));
	lengthColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("miles"));
	elevationColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("elevationInFeet"));
	difficultyColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("difficultyLevel"));
	typeColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("type"));
	trailTableView.setItems(trailList);
	
	
	//wraps observableList
	System.out.println(trailList);
	FilteredList<Trails> filteredData=new FilteredList<>(trailList);
	//setting filter predicate
	searchField.textProperty().addListener((observable, oldValue, newValue)->{
		filteredData.setPredicate(createPredicate(newValue));
	});
	
	//Sorted list
	SortedList<Trails> sortedData=new SortedList<>(filteredData);
	//stuffer
	sortedData.comparatorProperty().bind(trailTableView.comparatorProperty());
	trailTableView.setItems(sortedData);
	
	//comboBox filter
	
	//comboBox.setEditable(true);
	
	
	FilteredList<Trails> filteredComboData=new FilteredList<>(trailList);	//setting filter predicate
	comboBox.getEditor().textProperty().addListener((observable, oldValue, newValue)->{
		filteredComboData.setPredicate(createPredicate(newValue));
	});
	
	//Sorted list
	SortedList<Trails> sortedList=new SortedList<>(filteredComboData);
	//stuffer
	sortedList.comparatorProperty().bind(trailTableView.comparatorProperty());
	trailNameColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("trailName"));
	addressColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("address"));
	lengthColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("miles"));
	elevationColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("elevationInFeet"));
	difficultyColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("difficultyLevel"));
	typeColumn.setCellValueFactory(new PropertyValueFactory<Trails,String>("type"));
	trailTableView.setItems(sortedList);
	
		
		System.out.println(users);
		System.out.println(trails);
		System.out.println(history);
		
		
		
	}
	
	private boolean searchFindsOrder(Trails trail, String searchText) {
		return (trail.getTrailName().toLowerCase().contains(searchText.toLowerCase()));
	}
	
	private Predicate<Trails> createPredicate(String searchText){
		return trail->{
			if(searchText==null||searchText.isEmpty()) return true;
			return searchFindsOrder(trail,searchText);
		};
	}
	
	
}
