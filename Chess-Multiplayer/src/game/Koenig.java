package game;

public class Koenig extends Figure{

	public Koenig(int x, int y, COLOR color) {
		super(x, y, TYPE.KOENIG, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkFields() {
		//	   ooo
		//	   oXo
		//	   ooo
		
		int checkX = x;
		int checkY = y;
		
		//upper row
		checkY = y-1;
		for(checkX = x-1; checkX <= x + 1; checkX++){
			if(Board.isOnBoard(checkX, checkY)){
				Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null? true : checkColor(checkX, checkY);
			}
		}
		
		//left
		checkX = x-1;
		checkY = y;
		if(Board.isOnBoard(checkX, checkY)){
			Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null? true : checkColor(checkX, checkY);
		}
		//right
		checkX = x+1;
		checkY = y;
		if(Board.isOnBoard(checkX, checkY)){
			Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null? true : checkColor(checkX, checkY);
		}
		//bottom row
		checkY = y+1;
		for(checkX = x-1; checkX <= x + 1; checkX++){
			if(Board.isOnBoard(checkX, checkY)){
				Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null? true : checkColor(checkX, checkY);
			}
		}
	}

}
