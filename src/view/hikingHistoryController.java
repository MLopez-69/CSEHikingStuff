package view;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import hikers.Trails;
import hikingHistory.hikingHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import login.User;
import login.main;

public class hikingHistoryController implements Initializable{
	private TreeMap<String, hikingHistory> history;
	private final ObservableList<hikingHistory> hiking = FXCollections.observableArrayList();
	
	  @FXML
	    private TableView<hikingHistory> tableView;

	    @FXML
	    private TableColumn<hikingHistory, String> trailNameColumn;

	    @FXML
	    private TableColumn<hikingHistory, String> dateStartColumn;

	    @FXML
	    private TableColumn<hikingHistory, String> dateFinishColumn;

	    @FXML
	    private TableColumn<hikingHistory, String> distanceColumn;

	    @FXML
	    private TableColumn<hikingHistory, String> durationColumn;

	    @FXML
	    private TableColumn<hikingHistory, String> paceColumn;
	
	    @FXML
	    private Button addButton;

	    @FXML
	    private TextField trailNameField;

	    @FXML
	    private TextField dateStartField;

	    @FXML
	    private TextField distanceField;

	    @FXML
	    private TextField dateFinishField;

	    @FXML
	    private TextField durationField;

	    @FXML
	    private TextField paceField;

    @FXML
    private Button backButton;

    @FXML
    void addTrail(ActionEvent event) {
    	hikingHistory history=new hikingHistory(trailNameField.getText(),dateStartField.getText(),dateFinishField.getText()
    			,distanceField.getText(),durationField.getText(), "",paceField.getText());
    	
    	//get items from table
    	this.history.put(trailNameField.getText(), history);
    	
    	tableView.getItems().add(history);
    	
    }

    @FXML
    void backTrack(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/login/home.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    public void initTable(TreeMap<String,hikingHistory> history) {
		Iterator<Map.Entry<String,hikingHistory>> historyIterator=history.entrySet().iterator();
		while(historyIterator.hasNext()) {
			Map.Entry<String, hikingHistory> entry=historyIterator.next();
			hiking.add(entry.getValue());
		}
	
		
		trailNameColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("trailName"));
		dateStartColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("dateAndStartTime"));
		dateFinishColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("dateAndEndTime"));
		distanceColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("distanceHiked"));
		durationColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("duration"));
		paceColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("averagePace"));
		tableView.setItems(hiking);
		
		
    }
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		history=main.getHistory();
		
		Iterator<Map.Entry<String,hikingHistory>> historyIterator=history.entrySet().iterator();
		while(historyIterator.hasNext()) {
			Map.Entry<String, hikingHistory> entry=historyIterator.next();
			hiking.add(entry.getValue());
		}
	
		
		trailNameColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("trailName"));
		dateStartColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("dateAndStartTime"));
		dateFinishColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("dateAndEndTime"));
		distanceColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("distanceHiked"));
		durationColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("duration"));
		paceColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("averagePace"));
		tableView.setItems(hiking);
		
		//makes table editable
		tableView.setEditable(true);
//		trailNameColumn.setCellFactory(textFieldTableCell,forTableColumn());
//		dateStartColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("dateAndStartTime"));
//		dateFinishColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("dateAndEndTime"));
//		distanceColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("distanceHiked"));
//		durationColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("duration"));
//		paceColumn.setCellValueFactory(new PropertyValueFactory<hikingHistory,String>("averagePace"));
		
	}
}
