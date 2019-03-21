
public class Block_I extends Block{
	Block_I(){
		sqs = new Square[4];
		color = 1;
		sqs[0] = SqArr.makeSquare(1, 4, color);
		sqs[1] = SqArr.makeSquare(2, 4, color);
		sqs[2] = SqArr.makeSquare(3, 4, color);
		sqs[3] = SqArr.makeSquare(4, 4, color);
		for(int i = 0; i < sqs.length; i++) {
			r[i] = sqs[i].getRect();
		}
	}
}
