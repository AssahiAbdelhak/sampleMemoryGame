package application;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;



public class gameController implements Initializable{

	@FXML
	private GridPane grid;
	
	Integer[] intlist = new Integer[36];
	
	List<Integer> shuffled;
	final int NUMBEROFBLOCKS = 9;
	int cmp = 1;
	@FXML
    private Button start;

	EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
        @Override 
        public void handle(MouseEvent e) { 
        	
			Label label = (Label) e.getTarget();
			if(Integer.parseInt(label.getId())==cmp) {
				label.setText(String.valueOf(cmp));
				if(cmp==NUMBEROFBLOCKS) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setContentText("You won");
					alert.show();
				}
				cmp++;
			}else if(Integer.parseInt(label.getId())>cmp) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("You Lose");
				alert.showAndWait();
				Platform.exit();
			}
        } 
     };
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < 36 ; i++)
			intlist[i] = i;
		
		shuffled = Arrays.asList(intlist);
		
		Collections.shuffle(shuffled);
		for(int i = 0; i < NUMBEROFBLOCKS;i++) {
			Pane pane = new Pane();
			Label label = new Label();
			
			pane.getStyleClass().add("panel");
			  
		    pane.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
			
			pane.getChildren().add(label);
			
			int row = shuffled.get(i)/8;
			int column = shuffled.get(i) %8 ;
			grid.add(pane, column, row);
			
			
		}
		

	}

	
	@FXML
    void start() {
		printAndWait();
		
		PauseTransition wait = new PauseTransition(Duration.seconds(5));
        wait.setOnFinished((e) -> {
            /*YOUR METHOD*/
            clear();
        });
        wait.play();
		
    }
	
	
	private void printAndWait() {
		// TODO Auto-generated method stub
		for(int i = 0; i < NUMBEROFBLOCKS;i++) {
			Pane pane = (Pane) grid.getChildren().get(i);
			Label label = (Label) pane.getChildren().get(0);
			label.getStyleClass().add("label");
			label.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			label.setText(String.valueOf(i+1));
			label.setId(String.valueOf(i+1));
			System.out.println(label.getHeight());
		}
	}

	private void clear() {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < NUMBEROFBLOCKS;i++) {
			Pane pane = (Pane) grid.getChildren().get(i);
			Label label = (Label) pane.getChildren().get(0);
			label.setText("");
		}
	}

}
