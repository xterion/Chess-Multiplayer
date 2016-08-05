package game;

public class Laeufer extends Figure{

	public Laeufer(int x, int y, COLOR color) {
		super(x, y, TYPE.LAEUFER, color);
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
		//     * *
		//	    X
		//	   * *
		
		int checkX = x;
		int checkY = y;
		
		//up-left
		checkX = x-1;
		checkY = y-1;
		do{
			if(!Board.isOnBoard(checkX, checkY)) break;
			Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null? true : checkColor(checkX, checkY);
			checkX--;
			checkY--;
		}while(Board.field[checkX][checkY]== null);
		
		//up-right
		checkX = x+1;
		checkY = y-1;
		do{
			if(!Board.isOnBoard(checkX, checkY)) break;
			Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null? true : checkColor(checkX, checkY);
			checkX++;
			checkY--;
		}while(Board.field[checkX][checkY]== null);
		
		//down-left
		checkX = x-1;
		checkY = y+1;
		do{
			if(!Board.isOnBoard(checkX, checkY)) break;
			Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null? true : checkColor(checkX, checkY);
			checkX--;
			checkY++;
		}while(Board.field[checkX][checkY]== null);
		
		//down-right
		checkX = x+1;
		checkY = y+1;
		do{
			if(!Board.isOnBoard(checkX, checkY)) break;
			Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null? true : checkColor(checkX, checkY);
			checkX++;
			checkY++;
		}while(Board.field[checkX][checkY]== null);
	}

}
