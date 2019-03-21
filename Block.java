
import javafx.scene.shape.Rectangle;

public abstract class Block extends Physics{
	protected Square[] sqs;
	protected Rectangle[] r = new Rectangle[4];
	protected int color;

	public Square[] getSquares() {
		return sqs;
	}
	public int getColor() {
		return color;
	}
	public Rectangle[] getRects() {
		return r;
	}
	
	public void moveDown() {
		setValueToAll(false);
		boolean flag = true;
		for(int i = 0; i < 4; i++) {
			boolean state = sqs[i].isAbleToMoveDown();
			if(!state) {
				flag = false;
			}
		}
		if(flag) {
			for(int i = 3; i >= 0; i--)  {
				sqs[i].moveDown();
			}
		}
		for(int i = 0; i < 4; i++) {
			SqArr.setValueSquare(sqs[i], sqs[i].getPosY()/40, sqs[i].getPosX()/40);
		}
	}
	public void setAsPreview() {
		setValueToAll(false);
		for(int i = 0; i < sqs.length; i++) {
			sqs[i].moveByVector(8, 6);
		}
	}
	public boolean isAbleToMoveDown() {
		setValueToAll(false);
		boolean flag = true;
		for(int i = 0; i < 4; i++) {
			flag = sqs[i].isAbleToMoveDown();
			if(!flag) {
				flag = false;
				break;
			}
		}
		setValueToAll(true);
		return flag;
	}
	public void moveRight() {
		setValueToAll(false);
		for(int i = 0; i < sqs.length; i++) {
			if(sqs[i].getPosX()+40 < 400) {
				if(SqArr.getSqArray()[sqs[i].getPosY()/40][(sqs[i].getPosX()/40)+1] != null) {
					setValueToAll(true);
					return;
				}
			}
			else {
				setValueToAll(true);
				return;
			}
		}
		for(int i = 3; i >= 0; i--) {
			sqs[i].moveRight();
		}
		for(int i = 3; i >= 0; i--) {
			SqArr.setValueSquare(sqs[i], sqs[i].getPosY()/40, sqs[i].getPosX()/40);
		}
	}
	public void moveLeft() {
		setValueToAll(false);
		for(int i = 0; i < sqs.length; i++) {
			if(sqs[i].getPosX() > 0) {
				if(SqArr.getSqArray()[sqs[i].getPosY()/40][(sqs[i].getPosX()/40)-1] != null) {
					setValueToAll(true);
					return;
				}
			}
			else {
				setValueToAll(true);
				return;
			}
		}
		for(int i = 3; i >= 0; i--){
			sqs[i].moveLeft();
		}
		for(int i = 3; i >= 0; i--) {
			SqArr.setValueSquare(sqs[i], sqs[i].getPosY()/40, sqs[i].getPosX()/40);
		}
	}
	public void setValueToAll(boolean value) {
		if(!value) {
			for(int i = 0; i < sqs.length; i++) {
				SqArr.getSqArray()[sqs[i].getPosY()/40][(sqs[i].getPosX()/40)] = null;
			}
		}
		else {
			for(int i = 0; i < sqs.length; i++) {
				SqArr.getSqArray()[sqs[i].getPosY()/40][(sqs[i].getPosX()/40)] = sqs[i];
			}
		}
	}
	public void rotateLeft() {
		int[][] coor = new int[4][2];
		int[][] diff = new int[4][2];
		setValueToAll(false);
		coor = getCoor();
		diff = getCoor();
		int pivotX = sqs[1].getPosX()/40;
		int pivotY = sqs[1].getPosY()/40;
		
		coor = changeByPivot(coor, pivotX, pivotY, '-');
		coor = multiplyByMatLeft(coor);
		coor = changeByPivot(coor, pivotX, pivotY, '+');
		diff = getDiff(coor, diff);
		if(!checkIfCanRotate(diff))
			return;
		for(int i = 0; i < 4; i++) {
			sqs[i].moveByVector(diff[i][0], diff[i][1]);
		}
		for(int i = 3; i >= 0; i--) {
			SqArr.setValueSquare(sqs[i], sqs[i].getPosY()/40, sqs[i].getPosX()/40);
		}
	}
	public void rotateRight() {
		int[][] coor = new int[4][2];
		int[][] diff = new int[4][2];
		setValueToAll(false);
		coor = getCoor();
		diff = getCoor();
		int pivotX = sqs[1].getPosX()/40;
		int pivotY = sqs[1].getPosY()/40;
		
		coor = changeByPivot(coor, pivotX, pivotY, '-');
		coor = multiplyByMatRight(coor);
		coor = changeByPivot(coor, pivotX, pivotY, '+');
		diff = getDiff(coor, diff);
		if(!checkIfCanRotate(diff))
			return;
		for(int i = 0; i < 4; i++) {
			sqs[i].moveByVector(diff[i][0], diff[i][1]);
		}
		for(int i = 3; i >= 0; i--) {
			SqArr.setValueSquare(sqs[i], sqs[i].getPosY()/40, sqs[i].getPosX()/40);
		}
	}
	private boolean checkIfCanRotate(int[][] diff) {
		for(int i = 0; i < 4; i++) {
			if(sqs[i].getPosX()/40 + diff[i][0] > 9 || 
					sqs[i].getPosX()/40 + diff[i][0] < 0 ||
					sqs[i].getPosY()/40 + diff[i][1] > 23) {
				setValueToAll(true);
				return false;
			}
			if(SqArr.getSqArray()[sqs[i].getPosY()/40 + diff[i][1]][sqs[i].getPosX()/40 + diff[i][0]] != null) {
				setValueToAll(true);
				return false;
			}
		}
		return true;
	}
	private int[][] getCoor() {
		int[][] coor = new int[4][2];
		for(int i = 0; i < 4; i++) {
			coor[i][0] = sqs[i].getPosX()/40;
			coor[i][1] = sqs[i].getPosY()/40;
		}
		return coor;
	}
	private int[][] getDiff(int[][] coor, int[][] diff){
		for(int i = 0; i < 4; i++) {
			diff[i][0] = coor[i][0] - diff[i][0];
			diff[i][1] = coor[i][1] - diff[i][1];
		}
		return diff;
	}
	private int[][] changeByPivot(int[][] coor, int pivotX, int pivotY, char sign){
		for(int i = 0; i < 4; i++) {
			if(sign == '-') {
			coor[i][0]-=pivotX;
			coor[i][1]-=pivotY;
			}
			if(sign == '+') {
				coor[i][0]+=pivotX;
				coor[i][1]+=pivotY;
			}
		}
		return coor;
	}
	private int[][] multiplyByMatRight(int[][] coor){
		int sin[][] = new int[2][2];
		int res[][] = new int[4][2];
		sin[0][0] = 0; sin[0][1] = -1;
		sin[1][0] = 1; sin[1][1] = 0;
		for (int i = 0; i < 4; i++) { 
		    for (int j = 0; j < 2; j++) { 
		        for (int k = 0; k < 2; k++) { 
		            res[i][j] += sin[j][k] * coor[i][k];
		        }
		    }
		}
		return res;
	}
	private int[][] multiplyByMatLeft(int[][] coor){
		int sin[][] = new int[2][2];
		int res[][] = new int[4][2];
		sin[0][0] = 0; sin[0][1] = 1;
		sin[1][0] = -1; sin[1][1] = 0;
		for (int i = 0; i < 4; i++) { 
		    for (int j = 0; j < 2; j++) { 
		        for (int k = 0; k < 2; k++) { 
		            res[i][j] += sin[j][k] * coor[i][k];
		        }
		    }
		}
		return res;
	}
}

















