package game;

public class Turm extends Figure {

	private boolean moved = false;

	public Turm(int x, int y, COLOR color) {
		super(x, y, TYPE.TURM, color);
		// TODO Auto-generated constructor stub
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	@Override
	public void checkFields() {
		//      *
		//	   *X*
		//      *

		int checkX = x;
		int checkY = y;

		//up
		checkX = x;
		checkY = y - 1;
		while (Board.isOnBoard(checkX, checkY)) {
			if (Board.field[checkX][checkY] == null) {
				Board.moves[checkX][checkY] = true;
			} else {
				Board.moves[checkX][checkY] = checkColor(checkX, checkY);
				break;
			}
			Board.moves[x][checkY] = Board.field[x][checkY] == null ? true
					: checkColor(x, checkY);
			checkY--;
		}

		//down
		checkX = x;
		checkY = y + 1;
		while (Board.isOnBoard(checkX, checkY)) {
			if (Board.field[checkX][checkY] == null) {
				Board.moves[checkX][checkY] = true;
			} else {
				Board.moves[checkX][checkY] = checkColor(checkX, checkY);
				break;
			}
			checkY++;
		}

		//left
		checkX = x - 1;
		checkY = y;
		while (Board.isOnBoard(checkX, checkY)) {
			if (Board.field[checkX][checkY] == null) {
				Board.moves[checkX][checkY] = true;
			} else {
				Board.moves[checkX][checkY] = checkColor(checkX, checkY);
				break;
			}
			checkX--;
		}

		//right
		checkX = x + 1;
		checkY = y;
		while (Board.isOnBoard(checkX, checkY)) {
			if (Board.field[checkX][checkY] == null) {
				Board.moves[checkX][checkY] = true;
			} else {
				Board.moves[checkX][checkY] = checkColor(checkX, checkY);
				break;
			}
			checkX++;
		}

	}

}
