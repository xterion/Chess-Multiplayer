package game;

public class Pferd extends Figure{

	public Pferd(int x, int y, COLOR color) {
		super(x, y, TYPE.PFERD, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkFields() {
		//     o.o
		//	  o...o
		//	    X
		//	  o...o
		//     o.o
		
		int checkX = x;
		int checkY = y;
		
		//2up-1left
		checkX = x-1;
		checkY = y-2;
		if(Board.isOnBoard(checkX, checkY)){
			Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null? true : checkColor(checkX, checkY);
		}
		
		//2up-1right
		checkX = x+1;
		checkY = y-2;
		if(Board.isOnBoard(checkX, checkY)){
			Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null? true : checkColor(checkX, checkY);
		}
		
		//1up-2left
		checkX = x-2;
		checkY = y-1;
		if(Board.isOnBoard(checkX, checkY)){
			Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null? true : checkColor(checkX, checkY);
		}
		
		//1up-2right
		checkX = x+2;
		checkY = y-1;
		if(Board.isOnBoard(checkX, checkY)){
			Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null? true : checkColor(checkX, checkY);
		}
		
		//1down-2left
		checkX = x-2;
		checkY = y+1;
		if(Board.isOnBoard(checkX, checkY)){
			Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null? true : checkColor(checkX, checkY);
		}
		
		//1down-2right
		checkX = x+2;
		checkY = y+1;
		if(Board.isOnBoard(checkX, checkY)){
			Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null? true : checkColor(checkX, checkY);
		}
		
		//2down-1left
		checkX = x-1;
		checkY = y+2;
		if(Board.isOnBoard(checkX, checkY)){
			Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null? true : checkColor(checkX, checkY);
		}
		
		//2down-1right
		checkX = x+1;
		checkY = y+2;
		if(Board.isOnBoard(checkX, checkY)){
			Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null? true : checkColor(checkX, checkY);
		}
		
		
	}

}
