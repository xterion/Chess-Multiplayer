package game;

public class Laeufer extends Figure{

	public Laeufer(int x, int y, COLOR color) {
		super(x, y, TYPE.LAEUFER, color);
		// TODO Auto-generated constructor stub
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
		while(Board.isOnBoard(checkX, checkY)){
			if(Board.field[checkX][checkY] == null){
				Board.moves[checkX][checkY] = true;
			}else{
				Board.moves[checkX][checkY] = checkColor(checkX, checkY);
				break;
			}
			checkX--;
			checkY--;
		}
		
		//up-right
		checkX = x+1;
		checkY = y-1;
		while(Board.isOnBoard(checkX, checkY)){
			if(Board.field[checkX][checkY] == null){
				Board.moves[checkX][checkY] = true;
			}else{
				Board.moves[checkX][checkY] = checkColor(checkX, checkY);
				break;
			}
			checkX++;
			checkY--;
		}
		
		//down-left
		checkX = x-1;
		checkY = y+1;
		while(Board.isOnBoard(checkX, checkY)){
			if(Board.field[checkX][checkY] == null){
				Board.moves[checkX][checkY] = true;
			}else{
				Board.moves[checkX][checkY] = checkColor(checkX, checkY);
				break;
			}
			checkX--;
			checkY++;
		}
		
		//down-right
		checkX = x+1;
		checkY = y+1;
		while(Board.isOnBoard(checkX, checkY)){
			if(Board.field[checkX][checkY] == null){
				Board.moves[checkX][checkY] = true;
			}else{
				Board.moves[checkX][checkY] = checkColor(checkX, checkY);
				break;
			}
			checkX++;
			checkY++;
		}
	}

}
