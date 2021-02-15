package login;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import hikers.User;
import hikers.hikingHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class historyPopController implements Initializable{
	
	private TreeMap<String, hikingHistory> history;
	private User userMan;
	public hikingHistory hiked;
	private final ObservableList<hikingHistory> hiking = FXCollections.observableArrayList();
	private hikingHistory selectedHistory;
	public LinkedList<String> pictures;
	
		@FXML
	    private Label nameLabel;

	    @FXML
	    private Label dateStartLabel;

	    @FXML
	    private Label dateEndLabel;

	    @FXML
	    private Label distanceLabel;

	    @FXML
	    private Label paceLabel;

	    @FXML
	    private Label addressLabel;

	    @FXML
	    private ImageView imageView;

	    @FXML
	    private Button lButton;

	    @FXML
	    private Button rButton;
	    
		private File filePath;
		
		@FXML
	    private Button picBtn;
	    
	    @FXML
	    void backTrack(ActionEvent event) throws IOException {
	    	Parent root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
	        Scene scene = new Scene(root);
	        
	        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        window.setScene(scene);
	        window.show();
	    }
	    
	    @FXML
	    void leftButtonClicked(ActionEvent event) {
	    	try {
				String temp=pictures.removeLast();
				Image hikIm=new Image(getClass().getResource
						(temp).toExternalForm());
				
				imageView.setImage(hikIm);
				pictures.push(temp);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
	    }

	    @FXML
	    void rightButtonClicked(ActionEvent event) {
	    	try {
				String temp=pictures.pop();
				pictures.add(temp);
				temp=pictures.pop();
				Image hikIm=new Image(getClass().getResource
						(temp).toExternalForm());
				
				imageView.setImage(hikIm);
				pictures.push(temp);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
	    }
	    
	    @FXML
	    void choosePic(ActionEvent event) {
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
	    		this.imageView.setImage(crime);
	    		try {
					//userMan.getHikingHistory().get(hiked.getTrailName()).getPictures().add(filePath.getAbsolutePath());
					pictures.add(filePath.getCanonicalPath());
					userMan.getHikingHistory().get(hiked.getTrailName()).setPictures(pictures);
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
	    		
	    	}catch(IOException e) {
	    		System.err.println(e.getMessage());
	    	}
	    }
	    

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			picBtn.setDisable(true);
			try {
				initLabels();
				
				String temp=pictures.pop();
			Image hikIm=new Image(getClass().getResource
					(temp).toExternalForm());
			
			imageView.setImage(hikIm);
				
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			
			
			System.out.println(hiked);
			
			
		}
		
		public void initLabels() {
			nameLabel.setText(hiked.getTrailName());
			dateStartLabel.setText(hiked.getDateAndStartTime());
			dateEndLabel.setText(hiked.getDateAndEndTime());
			distanceLabel.setText(hiked.getDistanceHiked());
			paceLabel.setText(hiked.getAveragePace());
			
			//addressLabel.setText(hiked.get);
		}
		
		
		public void setHiked(hikingHistory hiking, User user) {
			userMan=user;
			hiked=hiking;
			nameLabel.setText(hiked.getTrailName());
			dateStartLabel.setText(hiked.getDateAndStartTime());
			dateEndLabel.setText(hiked.getDateAndEndTime());
			distanceLabel.setText(hiked.getDistanceHiked());
			paceLabel.setText(hiked.getAveragePace());
			pictures=hiked.getPictures();
			
			try {
				String temp=pictures.pop();
				Image hikIm=new Image(getClass().getResource
						(temp).toExternalForm());
				
				imageView.setImage(hikIm);
				pictures.push(temp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
		}
	    
	    
	    
	    
	    
}
