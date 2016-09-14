package game;

public class Koenig extends Figure {

	private boolean moved = false;

	public Koenig(int x, int y, COLOR color) {
		super(x, y, TYPE.KOENIG, color);
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
		//	   ooo
		//	   oXo
		//	   ooo

		int checkX = x;
		int checkY = y;

		//upper row
		checkY = y - 1;
		for (checkX = x - 1; checkX <= x + 1; checkX++) {
			if (Board.isOnBoard(checkX, checkY)) {
				Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null ? true
						: checkColor(checkX, checkY);
			}
		}

		//left
		checkX = x - 1;
		checkY = y;
		if (Board.isOnBoard(checkX, checkY)) {
			Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null ? true
					: checkColor(checkX, checkY);
		}
		//right
		checkX = x + 1;
		checkY = y;
		if (Board.isOnBoard(checkX, checkY)) {
			Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null ? true
					: checkColor(checkX, checkY);
		}
		//bottom row
		checkY = y + 1;
		for (checkX = x - 1; checkX <= x + 1; checkX++) {
			if (Board.isOnBoard(checkX, checkY)) {
				Board.moves[checkX][checkY] = Board.field[checkX][checkY] == null ? true
						: checkColor(checkX, checkY);
			}
		}

		//Rochade links
		checkY = y;
		checkX = x - 2;
		if (!moved) // könig nicht bewegt
			if (Board.isOnBoard(checkX, checkY)) // sicherheitscheck koordinaten
				if (Board.field[0][checkY] instanceof Turm)
					if (!((Turm) Board.field[0][checkY]).isMoved()) // turm vorhanden und unbewegt
						if ((Board.field[1][checkY] == null)
								&& (Board.field[2][checkY] == null)
								&& (Board.field[3][checkY] == null)) {// keine andere Figur dazwischen
							// bedrohte Felder werden hier nicht berücksichtigt
							Board.moves[checkX][checkY] = true;
						}

		//Rochade rechts
		checkY = y;
		checkX = x + 2;
		if (!moved) // könig nicht bewegt
			if (Board.isOnBoard(checkX, checkY)) // sicherheitscheck koordinaten
				if (Board.field[7][checkY] instanceof Turm)
					if (!((Turm) Board.field[7][checkY]).isMoved()) // turm vorhanden und unbewegt
						if ((Board.field[6][checkY] == null)
								&& (Board.field[5][checkY] == null)) {// keine andere Figur dazwischen
							// bedrohte Felder werden hier nicht berücksichtigt
							Board.moves[checkX][checkY] = true;
						}
	}
}
