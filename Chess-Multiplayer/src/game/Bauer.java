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
			if(!Board.isOnBoard(x, y-1)) break;
			Board.moves[x][y-1] = canMove(x, y-1);
			if(y == 6 && Board.field[x][5] == null) Board.moves[x][4] = canMove(x, 4);
			break;
		case BLACK:
			if(!Board.isOnBoard(x, y+1)) break;
			Board.moves[x][y+1] = canMove(x, y+1);
			if(y == 1 && Board.field[x][2] == null) Board.moves[x][5] = canMove(x, 5);
			break;
		}
	}

}
