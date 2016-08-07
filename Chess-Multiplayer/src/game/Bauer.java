package game;

public class Bauer extends Figure {

	public Bauer(int x, int y, COLOR color) {
		super(x, y, TYPE.BAUER, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkFields() {
		// *
		// * *
		// X X

		switch (color) {
		case WHITE:
			if (!Board.isOnBoard(x, y - 1))
				break;
			Board.moves[x][y - 1] = checkColor(x, y - 1);
			if (y == 6 && Board.field[x][5] == null)
				Board.moves[x][4] = Board.field[x][4] == null ? true
						: checkColor(x, 4);
			if (x > 0)
				Board.moves[x - 1][y - 1] = Board.field[x - 1][y - 1] == null ? false
						: !checkColor(x - 1, y - 1);
			if (x < 7)
				Board.moves[x + 1][y - 1] = Board.field[x + 1][y - 1] == null ? false
						: !checkColor(x + 1, y - 1);
			break;
		case BLACK:
			if (!Board.isOnBoard(x, y + 1))
				break;
			Board.moves[x][y + 1] = checkColor(x, y + 1);
			if (y == 1 && Board.field[x][2] == null)
				Board.moves[x][3] = Board.field[x][3] == null ? true
						: checkColor(x, 3);
			if (x > 0)
				Board.moves[x - 1][y + 1] = Board.field[x - 1][y + 1] == null ? false
						: !checkColor(x - 1, y + 1);
			if (x < 7)
				Board.moves[x + 1][y + 1] = Board.field[x + 1][y + 1] == null ? false
						: !checkColor(x + 1, y + 1);
			break;
		}
	}

}
