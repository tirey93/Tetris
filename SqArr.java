import javafx.scene.layout.Pane;

public class SqArr extends Main{
	private static Square[][] squaresArray;
	SqArr(){
		squaresArray = new Square[24][10];
    	for(int i = 0; i < 24; i++) {
    		for(int j = 0; j < 10; j++) {
    			squaresArray[i][j] = null;
    		}
    	}
	}
	public static Square makeSquare(int y, int x, int clr) {
		squaresArray[y][x] = new Square(y*40, x*40, clr);
		return squaresArray[y][x];
	}
	public static void setSquare(int y, int x) {
		root.getChildren().add(squaresArray[y][x].getRect());
	}
	public static void deleteSquare(int y, int x) {
		squaresArray[y][x] = null;
	}
	public static void setValueSquare(Square val,int y, int x) {
		squaresArray[y][x] = val;
	}
	public static Square[][] getSqArray(){
		return squaresArray;
	}
	public static void showSquares() {
		for (int i = 0; i < 20; ++i) System.out.println();
		for(int i = 4; i < 24; i++) {
    		for(int j = 0; j < 10; j++) {
    			if(squaresArray[i][j] == null) {
    				System.out.format("0 ");
    			}
    			else {
    				System.out.format("x ");
    			}
    		}
    		System.out.println();
    	}
	}
	public static void clearAll() {
		root.getChildren().clear();
		new Control();
		for(int i = 0; i < 24; i++) {
    		for(int j = 0; j < 10; j++) {
    			if(squaresArray[i][j] != null)
    				deleteSquare(i, j);
    		}
    	}
	}
}
