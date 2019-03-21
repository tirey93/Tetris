
public class Block_O extends Block{
	Block_O(){
		sqs = new Square[4];
		color = 3;
		sqs[0] = SqArr.makeSquare(3, 4, color);
		sqs[1] = SqArr.makeSquare(3, 5, color);
		sqs[2] = SqArr.makeSquare(4, 4, color);
		sqs[3] = SqArr.makeSquare(4, 5, color);
		for(int i = 0; i < sqs.length; i++) {
			r[i] = sqs[i].getRect();
		}
	}
	public void rotateLeft() {}
	public void rotateRight() {}
}

















