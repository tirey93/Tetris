
public class Block_Z extends Block{
	Block_Z(){
		sqs = new Square[4];
		color = 7;
		sqs[0] = SqArr.makeSquare(3, 3, color);
		sqs[1] = SqArr.makeSquare(3, 4, color);
		sqs[2] = SqArr.makeSquare(4, 4, color);
		sqs[3] = SqArr.makeSquare(4, 5, color);
		for(int i = 0; i < sqs.length; i++) {
			r[i] = sqs[i].getRect();
		}
	}
}
