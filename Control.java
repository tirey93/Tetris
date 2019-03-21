import javax.security.auth.callback.TextInputCallback;

import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Control extends Physics{
	static Text textScore, textRecord, textLevel;
	private static Button start;
	private Button stop;
	private Button help;
	private Button fame;
	static boolean isStarted = false;
	Control(){	
		setTextScore();
    	setStartButton();
    	setStopButton();
    	setHelpButton();
    	setTextRecord();
    	setTextLevel();
    	setFameButton();
    	root.getChildren().addAll(textScore, textLevel, textRecord, 
    							fame, start, help, stop);
	}
	
	public static void refreshScore(String s) {
		textScore.setText(s);
	}
	public static void refreshLevel(String s) {
		textLevel.setText(s);
	}
	public static void refreshRecord(String s) {
		textRecord.setText(s);
	}
	static void setTextRecord() {
		textRecord = new Text();
		textRecord.setX(450);
		textRecord.setY(30);
		textRecord.setFont(Font.font ("Verdana", 30));
		String s = "Current \nRecord:\n   " + record;
		textRecord.setText(s);
	}
	private void setTextScore() {
		textScore = new Text();
		textScore.setX(420);
		textScore.setY(350);
		textScore.setFont(Font.font ("Verdana", 30));
		textScore.setText("Score: 0");
	}
	public static void setGameOverWindow() {
		Text text = new Text();
		Rectangle bcgrnd = new Rectangle(0,300,402,150);
		TextArea textBox = new TextArea();
		setBcgrndGameOver(bcgrnd);
		setTextGameOver(text);
		setTextBoxGameOver(textBox);
		textBox.setOnKeyPressed((value)->{
			if(value.getCode() == KeyCode.ENTER) {
				WinnerList.addNewResult(fixName(textBox.getText()), score, level);
				WinnerList.storeToFile();
				root.getChildren().removeAll(bcgrnd,textBox, text);
			}
		});
		showGameOver(text);
		root.getChildren().addAll(bcgrnd,text);
		if(WinnerList.getPlace(score) <= 10) {
			root.getChildren().add(textBox);
		}
		else {
			text.setY(380);
		}
	}
	private static void setBcgrndGameOver(Rectangle r) {
		r.setFill(Color.BLUE);
	}
	private static void setTextGameOver(Text t) {
		t.setX(80);
		t.setY(330);
		t.setFont(Font.font ("Verdana", 30));
		t.setFill(Color.RED);
	}
	private static void setTextBoxGameOver(TextArea t) {
		t.setFocusTraversable(false);
		t.setFont(Font.font ("Verdana", 18));
		t.setPromptText("Enter your name.");
		t.setPrefColumnCount(10);
		t.getText();
		t.setTranslateX(75);
		t.setTranslateY(400);
		t.setMaxWidth(250);
		t.setMinWidth(250);
		t.setMinHeight(35);
		t.setMaxHeight(35);
	}
	private static void showGameOver(Text t) {
		if(WinnerList.getPlace(score) == 1) {
			t.setText("Game Over! \nNew Record!");
			record = score;
			String s = "Current \nRecord:\n   " + record;
			refreshRecord(s);
		}
		else if(WinnerList.getPlace(score) <= 10){
			t.setText("    Game Over! \nYou take " + WinnerList.getPlace(score) 
			+ " place.");
		}
		else {
			t.setText("    Game Over!");
		}
	}
	private static String fixName(String old) {
		char[] s = old.toCharArray();
		for(int i = 0; i < old.length(); i++) {
			if(old.charAt(i) == ' ') {
				s[i] = '_';
			}
		}
		String newOne = String.valueOf(s);
		return newOne;
	}
	public static void setTextLevel() {
		textLevel = new Text();
		textLevel.setX(420);
		textLevel.setY(390);
		textLevel.setFont(Font.font ("Verdana", 30));
		String t = "Level: " + level;
		textLevel.setText(t);
	}
	private void setStartButton() {
		start = new Button("New Game");
    	start.setMinWidth(100);
    	start.setMinHeight(80);
    	start.setTranslateX(450);
    	start.setTranslateY(590);
    	start.setFocusTraversable(false);
    	start.setOnAction((event)->{
    		isStarted = true;
    		if(isStarted) {
    			t1.stop();
    			try {
    				t1.getKeyFrames().remove(0);
    			}
    			catch(IndexOutOfBoundsException ex) {}
    		}
    		start.setText("Reset Game");
    		SqArr.clearAll();
			score = 0;
			time = 600;
			level = 1;
			refreshScore("Score: 0");
			refreshLevel("Level: 1");
    		Physics.setDroping();
    		scoreCount = 0;
    	}); 
    	
	}
	private void setHelpButton() {
		help = new Button("Show Help");
		help.setMinWidth(100);
		help.setMinHeight(40);
		help.setTranslateX(450);
		help.setTranslateY(530);
		help.setFocusTraversable(false);
		help.setOnAction((event)->{
			if(!isStarted) {
				Alert alert = new Alert(AlertType.CONFIRMATION, getHelp(), ButtonType.OK);
				alert.setResizable(true);
				alert.setHeaderText("Help");
		        alert.showAndWait();
			}
    	}); 
    	
	}
	private void setFameButton() {
		fame = new Button("Show Hall Of Fame");
		fame.setMinWidth(100);
		fame.setMinHeight(40);
		fame.setTranslateX(443);
		fame.setTranslateY(470);
		fame.setFocusTraversable(false);
		fame.setOnAction((event)->{
			if(!isStarted) {
				Alert alert = new Alert(AlertType.CONFIRMATION, 
						"Nr\tName\tScore Lvl\n\n" + WinnerList.getString(), ButtonType.OK);
		        alert.setHeaderText("Hall Of Fame");
		        alert.setResizable(true);
		        alert.showAndWait();
			}
    	}); 
    	
	}
	public void setStopButton() {
		stop = new Button("Stop Game");
		stop.setMinWidth(100);
		stop.setMinHeight(80);
		stop.setTranslateX(450);
		stop.setTranslateY(690);
		stop.setFocusTraversable(false);
		stop.setOnAction((event)->{
			if(isStarted) {
				t1.stop();
				try {
    				t1.getKeyFrames().remove(0);
    			}
    			catch(IndexOutOfBoundsException ex) {}
			}
			start.setText("New Game");
			SqArr.clearAll();
			score = 0;
			refreshScore("Score: 0");
			isStarted = false;
    	}); 
	}
	private String getHelp() {
		return "\tMove or rotate blocks to create a horizontal line of ten units "
				+ "without gaps. For each line points are rewarded. Your aim is to "
				+ "beat current record. Good luck!\n\n"
				+ "Control:\n"
				+ "\tLeft/Right to move Left/Right\n"
				+ "\tCTRL+Left/Right to rotate Left/Right\n"
				+ "\tDown Button to speed up move down\n"
				+ "\tSPACE to fast move down";
	}
}











