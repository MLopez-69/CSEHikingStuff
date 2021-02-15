package login;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.function.Predicate;

import hikers.Trails;
import hikers.User;
import hikers.hikingHistory;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class hikingHistoryController implements Initializable{
	private TreeMap<String, hikingHistory> history;
	private User userMan;
	private hikingHistory hiked;
	private final ObservableList<hikingHistory> hiking = FXCollections.observableArrayList();
	private hikingHistory selectedHistory;
	
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
	    private TextArea warningBox;
	    @FXML
	    private Button backButton;
	    
	    private FilteredList<hikingHistory> filteredData;
	    

    @FXML
    void addTrail(ActionEvent event) {
    	if (!trailNameField.getText().isEmpty()&&trailNameField.getText()!=null) {
			hikingHistory history = new hikingHistory(trailNameField.getText(), dateStartField.getText(),
					dateFinishField.getText(), distanceField.getText(), durationField.getText(), "",
					paceField.getText());
			//get items from table
			this.history.put(dateStartField.getText(), history);
			hiking.add(history);
			//tableView.getItems().add(history);
		}
    }
    
    @FXML
    void deleteTrail(ActionEvent event) {
    	try {
			hiked=tableView.getSelectionModel().getSelectedItem();
			System.out.println(hiked);
			history.remove(hiked.getDateAndStartTime());
			hiking.removeAll(hiked);
			System.out.println(history);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			warningBox.setText("Please select a history \nbefore you delete it");
		}
    }

    @FXML
    void backTrack(ActionEvent event) throws IOException {
    	userMan.setHikingHistory(history);
    	main.setHistory(history);
    	Parent root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    void editTrail(ActionEvent event) {
    	
    	try {
			hikingHistory updatedHist= new hikingHistory(trailNameField.getText(),dateStartField.getText(),
					dateFinishField.getText(),distanceField.getText(),durationField.getText(),"",
					paceField.getText());
			
			history.remove(selectedHistory.getDateAndStartTime(), selectedHistory);
			hiking.remove(selectedHistory);
			hiking.add(updatedHist);
			history.put(updatedHist.getDateAndStartTime(),updatedHist);
			warningBox.clear();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			warningBox.setText("Please select a history \nbefore you edit it");
		}
    }
    
    //MOUSE CLICK EVENT
    @FXML
    void editHistoryClick(MouseEvent event) {
    	if(event.getClickCount()==2) {
    		try {
				selectedHistory=tableView.getSelectionModel().getSelectedItem();
				trailNameField.setText(selectedHistory.getTrailName());
				distanceField.setText(selectedHistory.getDistanceHiked());
				dateStartField.setText(selectedHistory.getDateAndStartTime());
				dateFinishField.setText(selectedHistory.getDateAndEndTime());
				durationField.setText(selectedHistory.getDuration());
				paceField.setText(selectedHistory.getAveragePace());
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
    		
    	}
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
    
//    @FXML
//    void searchTrail(ActionEvent event) {
//    	
//    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		userMan=main.getUserMan();
		history=userMan.getHikingHistory();
		
		initTable(history);
		
		//makes table editable
		tableView.setEditable(true);
		
		//wraps observableList
		filteredData = new FilteredList<hikingHistory>(hiking);
		//setting filter predicate
		trailNameField.textProperty().addListener((observable, oldValue, newValue)->{
			filteredData.setPredicate(createPredicate(newValue));
		});
		
		//Sorted list
		SortedList<hikingHistory> sortedData=new SortedList<>(filteredData);
		//stuffer
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());

		tableView.setItems(sortedData);
		
	}
	
	private boolean searchFindsOrder(hikingHistory trail, String searchText) {
		return (trail.getTrailName().toLowerCase().contains(searchText.toLowerCase()));
	}
	
	private Predicate<hikingHistory> createPredicate(String searchText){
		return trail->{
			if(searchText==null||searchText.isEmpty()) {
				return true;
			}
			return searchFindsOrder(trail,searchText);
		};
	}
	
}
