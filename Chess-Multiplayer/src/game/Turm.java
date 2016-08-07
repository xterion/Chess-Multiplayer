package game;

public class Turm extends Figure{

	public Turm(int x, int y, COLOR color) {
		super(x, y, TYPE.TURM, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkFields() {
		//      *
		//	   *X*
		//      *
	
		int checkX = x;
		int checkY = y;
		
		//up
		checkY = y-1;
		do{
			if(!Board.isOnBoard(x, checkY)) break;
			Board.moves[x][checkY] = Board.field[x][checkY] == null? true : checkColor(x, checkY);
			checkY--;
		}while(Board.field[x][checkY]== null);
		
		//down
		checkY = y+1;
		do{
			if(!Board.isOnBoard(x, checkY)) break;
			Board.moves[x][checkY] = Board.field[x][checkY] == null? true : checkColor(x, checkY);
			checkY++;
		}while(Board.field[x][checkY]== null);
		
		//left
		checkX = x-1;
		do{
			if(!Board.isOnBoard(checkX, y)) break;
			Board.moves[checkX][y] = Board.field[checkX][y] == null? true : checkColor(checkX, y);
			checkX--;
		}while(Board.field[checkX][y]== null);
		
		//right
		checkX = x+1;
		do{
			if(!Board.isOnBoard(checkX, y)) break;
			Board.moves[checkX][y] = Board.field[checkX][y] == null? true : checkColor(checkX, y);
			checkX++;
		}while(Board.field[checkX][y]== null);
		
	}

}
