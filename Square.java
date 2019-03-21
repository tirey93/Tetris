import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Square extends Physics{
	private Rectangle square;
	private int posY = -160;
	private int posX = 0;
	private int color;
	Square(int y, int x, int col){
		color = col;
		square = new Rectangle(posX, posY, 44, 44);
		posX = x;
		square.setTranslateX(posX);
		posY = y;
		square.setTranslateY(posY);
		String src = "img/" + color + ".png";
		InputStream in = getClass().getResourceAsStream(src);
		Image img = new Image(in);
		square.setFill(new ImagePattern(img));
	}
	public Rectangle getRect() {
		return square;
	}
	public void moveByVector(int x, int y) {
		SqArr.getSqArray()[posY/40][posX/40] = null;
		for(int i = 0; i < Math.abs(x); i++) {
			posX = posX + 40*(x/Math.abs(x));
		}
		for(int i = 0; i < Math.abs(y); i++) {
			posY = posY + 40*(y/Math.abs(y));
		}
		square.setTranslateY(posY);
		square.setTranslateX(posX);
	}
	protected void moveDown() {
		SqArr.getSqArray()[posY/40][posX/40] = null;
		posY+=40;
		square.setTranslateY(posY);

	}
	protected int getPosY() {
		return posY;
	}
	public int getPosX() {
		return posX;
	}
	public int getColor() {
		return color;
	}
	public boolean isAbleToMoveDown() {
		if(posY+40 < 960) {
			if(SqArr.getSqArray()[(posY/40)+1][posX/40] != null) {
				return false;
			}
			return true;
		}
		else {
			return false;
		}
	}
	public void moveRight() {
		SqArr.getSqArray()[posY/40][posX/40] = null;
		posX+=40;
		square.setTranslateX(posX);

	}
	public void moveLeft() {
		SqArr.getSqArray()[posY/40][posX/40] = null;
		posX-=40;
		square.setTranslateX(posX);
		
	}
}
