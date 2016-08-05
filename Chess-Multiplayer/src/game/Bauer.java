package game;

public class Bauer extends Figure{

	public Bauer(int x, int y, COLOR color) {
		super(x, y, TYPE.BAUER, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkFields() {
		//  *
		//  *   *
		//	X   X
		
		switch(color){
		case WHITE:
			Board.moves[x][y-1] = canMove(x, y-1);
			if(y == 6) Board.moves[x][y-2] = canMove(x, y-2);
			break;
		case BLACK:
			Board.moves[x][y+1] = canMove(x, y+1);
			if(y == 1) Board.moves[x][y+2] = canMove(x, y+2);
			break;
		}
	}

}
