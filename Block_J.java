
public class Block_J extends Block{
	Block_J(){
		sqs = new Square[4];
		color = 5;
		sqs[0] = SqArr.makeSquare(1, 5, color);
		sqs[1] = SqArr.makeSquare(2, 5, color);
		sqs[2] = SqArr.makeSquare(3, 5, color);
		sqs[3] = SqArr.makeSquare(3, 4, color);
		for(int i = 0; i < sqs.length; i++) {
			r[i] = sqs[i].getRect();
		}
	}
}
