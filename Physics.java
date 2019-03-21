import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sun.font.TextRecord;

public class Physics extends Main{
	protected static Timeline t1 = new Timeline();
	private static Block block;
	protected static int score = 0;
	protected static int scoreCount = 0;
	protected static int time = 600;
	private static int next;
	protected static int level = 1;
	private static Block prev = null;
	protected static int record = 0;
	Physics(){
		WinnerList.readFromFile();
		record = WinnerList.getRecord();
	}
    public static void setDroping() {
    	t1.setCycleCount(Animation.INDEFINITE);
    	drawBlock();
    	//block = new Block_O();
    	checkLvls();
    	root.getChildren().addAll(block.getRects());
    	KeyFrame k = new KeyFrame(Duration.millis(time), (event) -> {
			if(!block.isAbleToMoveDown()) {
				t1.stop();
				t1.getKeyFrames().remove(0);
				if(!checkIfLost()) {
					state.setValue(true);
				}
				else {
					Control.isStarted = false;
					Control.setGameOverWindow();
				}
			}
			else {
				block.moveDown();
			}
			//SqArr.showSquares();
    	});
    	t1.getKeyFrames().add(k);
    	t1.play();
    }
    private static boolean checkIfLost() {
    	for(int i = 0; i < 10; i++) {
    		if(SqArr.getSqArray()[4][i] != null){
    			return true;
    		}
    	}
    	return false;
    }
    private static void fastMoveDown() {
		if(block.isAbleToMoveDown()) {
			block.moveDown();
			fastMoveDown();
		}
	}
    private static void checkLvls() {
    	boolean state = true;
    	int sumScore = 0;
    	for(int i = 0; i < 24; i++) {
    		state = true;
	    	for(int j = 0; j < 10; j++) {
	    		if(SqArr.getSqArray()[i][j] == null) {
	    			state = false;
	    			break;
	    		}
	    	}
		    if(state) {
		    	scoreCount++;
		    	deleteRow(i);
		    	moveSquaresOneDown(i);
		    	sumScore++;
		    	if(scoreCount%4 == 0 && scoreCount != 0) {
		    		SpeedUp();
		    	}
		    }
    	}
    	if(sumScore != 0) {
	    	sumScores(sumScore);
	    	String s = "Score: " + score;
	    	Control.refreshScore(s);
    	}
    }
    private static void SpeedUp() {
    	time*=0.8;
    	level++;
    	String s = "Level: " + level;
    	Control.refreshLevel(s);
    }
    private static void sumScores(int sum) {
    	if(sum == 3) {
    		score += 40;
    	}
    	else if(sum == 4) {
    		score += 80;
    	}
    	else {
    		score += sum*10;
    	}
    }
    private static void moveSquaresOneDown(int lvl) {
    	for(int i = lvl-1; i > 4; i--) {
    		for(int j = 0; j < 10; j++) {
    			if(SqArr.getSqArray()[i][j] != null) {
    				if(i != 23) {
    					SqArr.getSqArray()[i+1][j] = SqArr.getSqArray()[i][j];
    				}
    			SqArr.getSqArray()[i][j].moveDown();
    			}
    		}
    	}
    }
    private static void deleteRow(int lvl) {
    	for(int j = 0; j < 10; j++) {
    		root.getChildren().remove(SqArr.getSqArray()[lvl][j].getRect());
    		SqArr.deleteSquare(lvl, j);
    	}
    }
    public static void bindKeys(Scene scene) {
    	scene.setOnKeyPressed((event)->{
    		try {
    		if(event.getCode() == KeyCode.LEFT && !event.isShortcutDown()) {
    			block.moveLeft();
    		}
    		if(event.getCode() == KeyCode.RIGHT && !event.isShortcutDown()) {
    			block.moveRight();
    		}
    		if(event.getCode() == KeyCode.SPACE) {
    			fastMoveDown();
    		}
    		if(event.getCode() == KeyCode.DOWN) {
    			block.moveDown();
    		}
    		if(event.getCode() == KeyCode.LEFT && event.isShortcutDown()) {
    			block.rotateLeft();
    		}
    		if(event.getCode() == KeyCode.RIGHT && event.isShortcutDown()) {
    			block.rotateRight();
    		}
    		}
    		catch(NullPointerException ex) {}
    	});
    }
    private static void drawBlock() {
	   Random r = new Random();
	   if(next != 0) {
		   root.getChildren().removeAll(prev.getRects());
	   }
	   if(next == 0)
		   next = r.nextInt(7)+1;
	   block = switchBlocks();
	   next = r.nextInt(7)+1;
	   prev = switchBlocks();
	   prev.setAsPreview();
	   root.getChildren().addAll(prev.getRects());
   }
    private static Block switchBlocks() {
    	switch(next) {
 	    case 1:
 		   return new Block_I();
 	    case 2:
 	       return new Block_T();
 	    case 3:
 		   return new Block_O();
 	    case 4:
 		   return new Block_L();
 	    case 5:
 		   return new Block_J();
 	    case 6:
 		   return new Block_S();
 	    case 7:
 		   return new Block_Z();
 		default:
 		   return null;
 	    }
    }
   public static Timeline getTimeline() {
	   return t1;
   }
   public static void setScore(int n) {
	   score = n;
   }
}













